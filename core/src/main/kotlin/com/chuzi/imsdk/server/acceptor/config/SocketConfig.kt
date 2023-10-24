package com.chuzi.imsdk.server.acceptor.config

import com.chuzi.imsdk.server.handler.KIMRequestHandler
import java.time.Duration

open class SocketConfig {

    private var port: Int? = null
    open fun getPort(): Int {
        return if ((port ?: 0) <= 0) DEFAULT_PORT else port ?: DEFAULT_PORT
    }

    fun setPort(port: Int?) {
        this.port = port
    }

    var outerRequestHandler: KIMRequestHandler? = null
    var isEnable = false
    var writeIdle: Duration = Duration.ofSeconds(45)
    var readIdle: Duration = Duration.ofSeconds(60)
    var maxPongTimeout = 1

    companion object {
        private const val DEFAULT_PORT = 23456
    }

}

