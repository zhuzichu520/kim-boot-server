package com.chuzi.imsdk.server.constant

import io.netty.util.AttributeKey

object ChannelAttr {
    val PING_COUNT = AttributeKey.valueOf<Int>("ping_count")
    val UID = AttributeKey.valueOf<String>("uid")
    val CHANNEL = AttributeKey.valueOf<String>("channel")
    val ID = AttributeKey.valueOf<String>("id")
    val DEVICE_ID = AttributeKey.valueOf<String>("device_id")
    val TAG = AttributeKey.valueOf<String>("tag")
    val LANGUAGE = AttributeKey.valueOf<String>("language")
}