package com.chuzi.kim.component.message

import com.chuzi.kim.component.event.MessageEvent
import com.chuzi.kim.utlis.JSONUtils
import com.chuzi.imsdk.server.group.SessionGroup
import com.chuzi.imsdk.server.model.Message
import jakarta.annotation.Resource
import org.springframework.context.event.EventListener
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class PushMessageListener : MessageListener {

    @Resource
    private lateinit var sessionGroup: SessionGroup

    override fun onMessage(redisMessage: org.springframework.data.redis.connection.Message, bytes: ByteArray?) {
        val message: Message = JSONUtils.fromJson(redisMessage.body, Message::class.java)
        this.onMessage(message)
    }

    @EventListener
    fun onMessage(event: MessageEvent) {
        this.onMessage(event.source)
    }

    fun onMessage(message: Message) {
        val uid = message.receiver ?: return
        sessionGroup.write(uid, message)
    }
}

