package com.chuzi.imsdk.server.group

import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.model.Message
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelFutureListener
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.function.Predicate
import java.util.stream.Collectors


open class SessionGroup : ConcurrentHashMap<String, MutableCollection<Channel>>() {

    @Transient
    private val remover: ChannelFutureListener = object : ChannelFutureListener {
        override fun operationComplete(future: ChannelFuture) {
            future.removeListener(this)
            remove(future.channel())
        }
    }

    protected open fun getKey(channel: Channel): String? {
        return channel.attr(ChannelAttr.UID).get()
    }

    fun remove(channel: Channel) {
        val uid = getKey(channel) ?: return
        val collections: MutableCollection<Channel> = getOrDefault(uid, mutableListOf())
        collections.remove(channel)
        if (collections.isEmpty()) {
            remove(uid)
        }
    }

    fun add(channel: Channel) {
        val uid = getKey(channel)
        if (uid == null || !channel.isActive) {
            return
        }
        channel.closeFuture().addListener(remover)
        val collections: MutableCollection<Channel>? = putIfAbsent(uid, ConcurrentLinkedQueue(Collections.singleton(channel)))
        collections?.add(channel)
        if (!channel.isActive) {
            remove(channel)
        }
    }

    @JvmOverloads
    fun write(key: String?, message: Message?, matcher: Predicate<Channel>? = Predicate { true }) {
        find(key).stream().filter(matcher).forEach { channel: Channel -> channel.writeAndFlush(message) }
    }


    fun write(key: String?, message: Message?, excludedSet: MutableCollection<String>?) {
        val predicate: Predicate<Channel> = ExcludedUidPredicate(excludedSet)
        this.write(key, message, predicate)
    }


    fun write(message: Message) {
        this.write(message.receiver, message)
    }

    fun find(key: String?): MutableCollection<Channel> {
        return this.getOrDefault(key, mutableListOf())
    }

    fun find(key: String?, matcher: Predicate<Channel>?): MutableCollection<Channel> {
        return this.find(key)
                .stream()
                .filter(matcher)
                .collect(Collectors.toList())
    }

    fun find(key: String?, vararg channel: String): MutableCollection<Channel> {
        val channels = mutableListOf(*channel)
        return find(key, channels)
    }

    fun find(key: String?, channelSet: MutableCollection<String>): MutableCollection<Channel> {
        val predicate: Predicate<Channel> = ChannelPredicate(channelSet)
        return find(key, predicate)
    }

    fun isManaged(channel: Channel): Boolean {
        val uid = getKey(channel)
        return if (uid == null || !channel.isActive) {
            false
        } else getOrDefault(uid, emptyList()).contains(channel)
    }

    private class ExcludedUidPredicate(excludedSet: MutableCollection<String>?) : Predicate<Channel> {
        private val excludedSet: Collection<String>

        init {
            this.excludedSet = excludedSet ?: emptySet()
        }

        override fun test(channel: Channel): Boolean {
            return !excludedSet.contains(channel.attr(ChannelAttr.UID).get())
        }
    }

    private class ChannelPredicate(channelSet: MutableCollection<String>?) : Predicate<Channel> {
        private val channelSet: Collection<String>

        init {
            this.channelSet = channelSet ?: emptySet()
        }

        override fun test(ioChannel: Channel): Boolean {
            return channelSet.contains(ioChannel.attr(ChannelAttr.CHANNEL).get())
        }
    }
}