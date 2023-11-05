package com.chuzi.imsdk.server.coder.json

import com.chuzi.imsdk.server.model.PingModel
import com.chuzi.imsdk.server.model.Transportable
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame


class TextMessageEncoder : MessageToMessageEncoder<Transportable>() {
    @Throws(JsonProcessingException::class)
    override fun encode(ctx: ChannelHandlerContext, data: Transportable, out: MutableList<Any>) {
        val protocol = TransmitBody()
        protocol.dataType = data.getDataType().value
        protocol.content = getBody(data)
        val frame = TextWebSocketFrame(OBJECT_MAPPER.writeValueAsString(protocol))
        out.add(frame)
    }

    @Throws(JsonProcessingException::class)
    private fun getBody(data: Transportable): String? {
        return if (data is PingModel) {
            null
        } else OBJECT_MAPPER.writeValueAsString(data)
    }

    companion object {
        private val OBJECT_MAPPER = JsonMapper.builder()
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .build()
    }
}

