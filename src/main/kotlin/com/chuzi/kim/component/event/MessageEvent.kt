package com.chuzi.kim.component.event

import com.chuzi.imsdk.server.model.MessageModel
import org.springframework.context.ApplicationEvent

class MessageEvent(messageModel: MessageModel) : ApplicationEvent(messageModel) {

    override fun getSource(): MessageModel {
        return source as MessageModel
    }

}

