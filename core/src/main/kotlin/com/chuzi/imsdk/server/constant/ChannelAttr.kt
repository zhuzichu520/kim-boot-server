package com.chuzi.imsdk.server.constant

import io.netty.util.AttributeKey

object ChannelAttr {
    val PING_COUNT: AttributeKey<Int> = AttributeKey.valueOf("ping_count")
    val UID: AttributeKey<String> = AttributeKey.valueOf("uid")
    val CHANNEL: AttributeKey<String> = AttributeKey.valueOf("channel")
    val ID: AttributeKey<String> = AttributeKey.valueOf("id")
    val DEVICE_ID: AttributeKey<String> = AttributeKey.valueOf("device_id")
    val TAG: AttributeKey<String> = AttributeKey.valueOf("tag")
    val LANGUAGE: AttributeKey<String> = AttributeKey.valueOf("language")
}