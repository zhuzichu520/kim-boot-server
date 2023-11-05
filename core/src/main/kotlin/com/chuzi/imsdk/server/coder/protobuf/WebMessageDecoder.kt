package com.chuzi.imsdk.server.coder.protobuf

import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.exception.ReadInvalidTypeException
import com.chuzi.imsdk.server.model.PongModel
import com.chuzi.imsdk.server.model.SentBodyModel
import com.chuzi.imsdk.server.model.proto.SentBodyProto
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufInputStream
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
import java.io.IOException
import java.io.InputStream


class WebMessageDecoder : MessageToMessageDecoder<BinaryWebSocketFrame>() {

    @Throws(Exception::class)
    override fun decode(context: ChannelHandlerContext, frame: BinaryWebSocketFrame, list: MutableList<Any>) {
        context.channel().attr(ChannelAttr.PING_COUNT).set(null)
        val buffer = frame.content()
        val type = buffer.readByte()
        if (DataType.PONG.value == type) {
            list.add(PongModel.getInstance())
            return
        }
        if (DataType.SENT.value == type) {
            list.add(getBody(buffer))
            return
        }
        throw ReadInvalidTypeException(type)
    }

    @Throws(IOException::class)
    private fun getBody(buffer: ByteBuf?): SentBodyModel {
        val inputStream: InputStream = ByteBufInputStream(buffer)
        val proto: SentBodyProto.SentBody = SentBodyProto.SentBody.parseFrom(inputStream)
        val body = SentBodyModel()
        body.data = proto.dataMap
        body.key = proto.key
        body.timestamp = proto.timestamp
        return body
    }
}
