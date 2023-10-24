package com.chuzi.kim.component.event

import com.chuzi.imsdk.server.model.Message
import org.springframework.context.ApplicationEvent

class MessageEvent(message: Message) : ApplicationEvent(message) {

    override fun getSource(): Message {
        return source as Message
    }

}

