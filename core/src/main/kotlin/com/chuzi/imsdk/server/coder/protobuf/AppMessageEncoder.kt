package com.chuzi.imsdk.server.coder.protobuf

import com.chuzi.imsdk.server.constant.KIMConstant
import com.chuzi.imsdk.server.model.Transportable
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder


class AppMessageEncoder : MessageToByteEncoder<Transportable>() {
    override fun encode(ctx: ChannelHandlerContext, data: Transportable, out: ByteBuf) {
        val body: ByteArray = data.getBody()
        val header = createHeader(data.getDataType().value, body.size)
        out.writeBytes(header)
        out.writeBytes(body)
    }

    private fun createHeader(type: Byte, length: Int): ByteArray {
        val header = ByteArray(KIMConstant.DATA_HEADER_LENGTH)
        header[0] = type
        header[1] = (length and 0xff).toByte()
        header[2] = (length shr 8 and 0xff).toByte()
        return header
    }
}

