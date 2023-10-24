package com.chuzi.imsdk.server.group

import com.chuzi.imsdk.server.constant.ChannelAttr
import io.netty.channel.Channel

class TagSessionGroup : SessionGroup() {
    override fun getKey(channel: Channel): String {
        return channel.attr(ChannelAttr.TAG).get()
    }
}