package com.chuzi.imsdk.server.handshake

import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.QueryStringDecoder
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete


class HandshakeEvent(
        private val uri: String,
        private val header: HttpHeaders
) {
    fun getHeader(name: String?): String {
        return header[name]
    }

    fun getHeaders(name: String?): List<String> {
        return header.getAll(name)
    }

    fun getIntHeader(name: String?): Int {
        return header.getInt(name)
    }

    fun getParameter(name: String?): String? {
        val decoder = QueryStringDecoder(uri)
        val valueList = decoder.parameters()[name]
        return if (valueList.isNullOrEmpty()) null else valueList[0]
    }

    fun getParameters(name: String?): List<String> {
        val decoder = QueryStringDecoder(uri)
        return decoder.parameters()[name]!!
    }

    companion object {
        fun of(event: HandshakeComplete): HandshakeEvent {
            return HandshakeEvent(event.requestUri(), event.requestHeaders())
        }
    }
}
