package com.chuzi.imsdk.server.coder.protobuf

import com.chuzi.imsdk.server.model.Transportable
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame


class WebMessageEncoder : MessageToMessageEncoder<Transportable>() {
    override fun encode(ctx: ChannelHandlerContext, data: Transportable, out: MutableList<Any>) {
        val body: ByteArray = data.getBody()
        val allocator = ctx.channel().config().allocator
        val buffer = allocator.buffer(body.size + 1)
        buffer.writeByte(data.getType().value.toInt())
        buffer.writeBytes(body)
        out.add(BinaryWebSocketFrame(buffer))
    }
}

