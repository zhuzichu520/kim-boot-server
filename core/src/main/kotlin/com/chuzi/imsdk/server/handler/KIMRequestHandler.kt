package com.chuzi.imsdk.server.handler

import com.chuzi.imsdk.server.model.SentBody
import io.netty.channel.Channel

interface KIMRequestHandler {
    fun process(channel: Channel, body: SentBody)
}

