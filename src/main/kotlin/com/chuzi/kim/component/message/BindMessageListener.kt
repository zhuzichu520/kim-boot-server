package com.chuzi.kim.component.message

import com.chuzi.kim.component.event.SessionEvent
import com.chuzi.kim.entity.Session
import com.chuzi.kim.utlis.JSONUtils
import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.group.SessionGroup
import com.chuzi.imsdk.server.model.Message
import io.netty.channel.Channel
import io.netty.channel.ChannelFutureListener
import jakarta.annotation.Resource
import org.apache.commons.lang3.ArrayUtils
import org.springframework.context.event.EventListener
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
import java.util.function.Consumer
import java.util.function.Predicate


@Component
class BindMessageListener : MessageListener {

    private val conflictMap: MutableMap<String, Array<String>> = HashMap()

    private val keepLiveChannels: MutableSet<String> = HashSet()

    @Resource
    private lateinit var sessionGroup: SessionGroup

    init {
        conflictMap[Session.CHANNEL_ANDROID] = arrayOf(Session.CHANNEL_ANDROID, Session.CHANNEL_IOS)
        conflictMap[Session.CHANNEL_IOS] = arrayOf(Session.CHANNEL_ANDROID, Session.CHANNEL_IOS)
        conflictMap[Session.CHANNEL_WINDOWS] =
            arrayOf(Session.CHANNEL_WINDOWS, Session.CHANNEL_WEB, Session.CHANNEL_MAC)
        conflictMap[Session.CHANNEL_WEB] = arrayOf(Session.CHANNEL_WINDOWS, Session.CHANNEL_WEB, Session.CHANNEL_MAC)
        conflictMap[Session.CHANNEL_MAC] = arrayOf(Session.CHANNEL_WINDOWS, Session.CHANNEL_WEB, Session.CHANNEL_MAC)
        keepLiveChannels.add(Session.CHANNEL_WEB)
    }

    @EventListener
    fun onMessage(event: SessionEvent) {
        handle(event.source)
    }

    override fun onMessage(redisMessage: org.springframework.data.redis.connection.Message, bytes: ByteArray?) {
        val session: Session = JSONUtils.fromJson(redisMessage.body, Session::class.java)
        handle(session)
    }

    private fun handle(session: Session) {
        val conflictChannels = conflictMap[session.channel] ?: return
        if (ArrayUtils.isEmpty(conflictChannels)) {
            return
        }
        val channelList = sessionGroup.find(session.uid, *conflictChannels) ?: return
        channelList.removeIf(KeepLivePredicate(session))
        channelList.stream().filter(SameDevicePredicate(session)).forEach { obj: Channel -> obj.close() }
        channelList.stream().filter(DifferentDevicePredicate(session))
            .forEach(BreakOffMessageConsumer(session.uid, session.deviceName))
    }

    private class BreakOffMessageConsumer(uid: String?, deviceName: String?) : Consumer<Channel> {
        private val message: Message = Message()

        init {
            message.action = FORCE_OFFLINE_ACTION
            message.receiver = uid
            message.sender = SYSTEM_ID
            message.content = deviceName
        }

        override fun accept(channel: Channel) {
            channel.writeAndFlush(message).addListener(ChannelFutureListener.CLOSE)
        }
    }

    private class SameDevicePredicate(session: Session) : Predicate<Channel> {
        private val deviceId: String?

        init {
            deviceId = session.deviceId
        }

        override fun test(channel: Channel): Boolean {
            return deviceId == channel.attr(ChannelAttr.DEVICE_ID).get()
        }
    }

    private class DifferentDevicePredicate(session: Session) : Predicate<Channel> {
        private val predicate: SameDevicePredicate

        init {
            predicate = SameDevicePredicate(session)
        }

        override fun test(channel: Channel): Boolean {
            return !predicate.test(channel)
        }
    }

    private inner class KeepLivePredicate(session: Session) : Predicate<Channel> {
        private val session: Session

        init {
            this.session = session
        }

        override fun test(ioChannel: Channel): Boolean {
            if (session.nid == ioChannel.attr(ChannelAttr.ID).get()) {
                return true
            }
            val deviceId = ioChannel.attr(ChannelAttr.DEVICE_ID).toString()
            val channel = ioChannel.attr(ChannelAttr.CHANNEL).toString()
            return keepLiveChannels.contains(channel) && session.deviceId == deviceId
        }
    }

    companion object {
        private const val FORCE_OFFLINE_ACTION = "999"
        private const val SYSTEM_ID = "0"
    }
}

