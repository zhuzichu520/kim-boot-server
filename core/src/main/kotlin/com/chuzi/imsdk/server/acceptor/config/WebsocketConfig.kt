package com.chuzi.imsdk.server.acceptor.config

import com.chuzi.imsdk.server.constant.WebsocketProtocol
import com.chuzi.imsdk.server.handshake.HandshakeEvent
import java.util.function.Predicate

class WebsocketConfig : SocketConfig() {
    companion object {
        private const val DEFAULT_PORT = 34567
        private const val DEFAULT_PATH = "/"
        private val DEFAULT_PROTOCOL: WebsocketProtocol = WebsocketProtocol.PROTOBUF
    }

    override fun getPort(): Int {
        return if (super.getPort() <= 0) DEFAULT_PORT else super.getPort()
    }

    var path: String? = null
        get() = if (field == null) DEFAULT_PATH else field
    var protocol: WebsocketProtocol? = null
        get() = if (field == null) DEFAULT_PROTOCOL else field

    var handshakePredicate: Predicate<HandshakeEvent>? = null

}
