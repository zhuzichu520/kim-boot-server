package com.chuzi.imsdk.server.coder.protobuf

import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.constant.KIMConstant
import com.chuzi.imsdk.server.exception.ReadInvalidTypeException
import com.chuzi.imsdk.server.model.Pong
import com.chuzi.imsdk.server.model.SentBody
import com.chuzi.imsdk.server.model.proto.SentBodyProto
import com.google.protobuf.InvalidProtocolBufferException
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class AppMessageDecoder : ByteToMessageDecoder() {
    @Throws(Exception::class)
    override fun decode(context: ChannelHandlerContext, buffer: ByteBuf, queue: MutableList<Any>) {
        context.channel().attr(ChannelAttr.PING_COUNT).set(null)
        if (buffer.readableBytes() < KIMConstant.DATA_HEADER_LENGTH) {
            return
        }
        buffer.markReaderIndex()
        val type = buffer.readByte()
        val lv = buffer.readByte()
        val hv = buffer.readByte()
        val length = getContentLength(lv, hv)
        if (buffer.readableBytes() < length) {
            buffer.resetReaderIndex()
            return
        }
        val content = ByteArray(length)
        buffer.readBytes(content)
        val message = mappingMessageObject(content, type)
        queue.add(message)
    }

    @Throws(InvalidProtocolBufferException::class)
    private fun mappingMessageObject(data: ByteArray, type: Byte): Any {
        if (DataType.PONG.value == type) {
            return Pong.getInstance()
        }
        if (DataType.SENT.value == type) {
            val bodyProto: SentBodyProto.SentBody = SentBodyProto.SentBody.parseFrom(data)
            val body = SentBody()
            body.data = bodyProto.dataMap
            body.key = bodyProto.key
            body.timestamp = bodyProto.timestamp
            return body
        }
        throw ReadInvalidTypeException(type)
    }

    private fun getContentLength(lv: Byte, hv: Byte): Int {
        val l = lv.toInt() and 0xff
        val h = hv.toInt() and 0xff
        return l or (h shl 8)
    }
}

