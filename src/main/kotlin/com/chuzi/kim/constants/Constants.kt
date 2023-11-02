package com.chuzi.kim.constants

import io.netty.util.AttributeKey

object Constants {

    const val PUSH_MESSAGE_INNER_QUEUE = "signal/channel/PUSH_MESSAGE_INNER_QUEUE"
    const val BIND_MESSAGE_INNER_QUEUE = "signal/channel/BIND_MESSAGE_INNER_QUEUE"
    const val APNS_DEVICE_TOKEN = "APNS_OPEN_%s"
    val SESSION_ID: AttributeKey<String> = AttributeKey.valueOf("session_id")

}

