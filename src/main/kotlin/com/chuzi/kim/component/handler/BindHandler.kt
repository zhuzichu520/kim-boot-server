package com.chuzi.kim.component.handler

import com.chuzi.kim.component.handler.annotation.KIMHandler
import com.chuzi.kim.component.redis.SignalRedisTemplate
import com.chuzi.kim.constants.Constants
import com.chuzi.kim.entity.Session
import com.chuzi.kim.service.SessionService
import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.group.SessionGroup
import com.chuzi.imsdk.server.handler.KIMRequestHandler
import com.chuzi.imsdk.server.model.ReplyBody
import com.chuzi.imsdk.server.model.SentBody
import io.netty.channel.Channel
import jakarta.annotation.Resource
import org.springframework.http.HttpStatus

@KIMHandler(key = "client_bind")
class BindHandler : KIMRequestHandler {

    @Resource
    private lateinit var sessionService: SessionService

    @Resource
    private lateinit var sessionGroup: SessionGroup

    @Resource
    private lateinit var signalRedisTemplate: SignalRedisTemplate

    override fun process(channel: Channel, body: SentBody) {
        if (sessionGroup.isManaged(channel)) {
            return
        }
        val reply = ReplyBody()
        reply.key = body.key
        reply.code = HttpStatus.OK.value().toString()
        reply.timestamp = System.currentTimeMillis()
        val uid = body["uid"]
        val session = Session()
        session.uid = uid
        session.nid = channel.attr(ChannelAttr.ID).get()
        session.deviceId = body["deviceId"]
        session.channel = body["channel"]
        session.deviceName = body["deviceName"]
        session.appVersion = body["appVersion"]
        session.osVersion = body["osVersion"]
        session.language = body["language"]
        channel.attr(ChannelAttr.UID).set(uid)
        channel.attr(ChannelAttr.CHANNEL).set(session.channel)
        channel.attr(ChannelAttr.DEVICE_ID).set(session.deviceId)
        channel.attr(ChannelAttr.LANGUAGE).set(session.language)
        sessionService.add(session)
        channel.attr(Constants.SESSION_ID).set(session.id)
        sessionGroup.add(channel)
        channel.writeAndFlush(reply)
        signalRedisTemplate.bind(session)
    }
}

