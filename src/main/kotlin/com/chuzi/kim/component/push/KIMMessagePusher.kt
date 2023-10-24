package com.chuzi.kim.component.push

import com.chuzi.imsdk.server.model.Message

interface KIMMessagePusher {

    fun push(message: Message?)

}

