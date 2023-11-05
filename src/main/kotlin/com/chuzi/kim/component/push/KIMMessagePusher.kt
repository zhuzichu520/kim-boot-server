package com.chuzi.kim.component.push

import com.chuzi.imsdk.server.model.MessageModel

interface KIMMessagePusher {

    fun push(messageModel: MessageModel?)

}

