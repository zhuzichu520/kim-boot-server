package com.chuzi.kim.component.push

import com.chuzi.imsdk.server.model.MessageModel
import com.chuzi.kim.component.redis.SignalRedisTemplate
import jakarta.annotation.Resource
import org.springframework.stereotype.Component

@Component
class DefaultMessagePusher : KIMMessagePusher {

    @Resource
    private lateinit var signalRedisTemplate: SignalRedisTemplate

    override fun push(messageModel: MessageModel?) {
        messageModel ?: return
        signalRedisTemplate.push(messageModel)
    }
}

