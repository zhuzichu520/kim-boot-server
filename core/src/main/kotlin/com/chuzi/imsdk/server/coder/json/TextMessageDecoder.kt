package com.chuzi.imsdk.server.coder.json

import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.exception.ReadInvalidTypeException
import com.chuzi.imsdk.server.model.Pong
import com.chuzi.imsdk.server.model.SentBody
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame

class TextMessageDecoder : MessageToMessageDecoder<TextWebSocketFrame>() {

    @Throws(Exception::class)
    override fun decode(context: ChannelHandlerContext, frame: TextWebSocketFrame, list: MutableList<Any>) {
        context.channel().attr(ChannelAttr.PING_COUNT).set(null)
        val text = frame.text()
        val protocol: TransmitBody = OBJECT_MAPPER.readValue(text, TransmitBody::class.java)
        if (protocol.type == DataType.PONG.value) {
            list.add(Pong.getInstance())
            return
        }
        if (protocol.type == DataType.SENT.value) {
            val body: SentBody = OBJECT_MAPPER.readValue(protocol.content, SentBody::class.java)
            list.add(body)
            return
        }
        throw ReadInvalidTypeException(protocol.type)
    }

    companion object {
        private val OBJECT_MAPPER = JsonMapper.builder().enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
            .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .serializationInclusion(JsonInclude.Include.NON_NULL).build()
    }
}