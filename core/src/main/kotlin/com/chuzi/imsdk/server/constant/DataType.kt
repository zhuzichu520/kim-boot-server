package com.chuzi.imsdk.server.constant

enum class DataType(value: Int) {

    PONG(0),
    PING(1),
    MESSAGE(2),
    SENT(3),
    REPLY(4);

    val value: Byte

    init {
        this.value = value.toByte()
    }
}

