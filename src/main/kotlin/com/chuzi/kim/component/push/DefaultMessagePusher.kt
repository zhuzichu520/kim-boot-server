package com.chuzi.kim.component.push

import com.chuzi.kim.component.redis.KeyValueRedisTemplate
import com.chuzi.kim.component.redis.SignalRedisTemplate
import com.chuzi.kim.service.APNsService
import com.chuzi.imsdk.server.model.Message
import jakarta.annotation.Resource
import org.springframework.stereotype.Component

@Component
class DefaultMessagePusher : KIMMessagePusher {

    @Resource
    private lateinit var apnsService: APNsService

    @Resource
    private lateinit var signalRedisTemplate: SignalRedisTemplate

    @Resource
    private lateinit var keyValueRedisTemplate: KeyValueRedisTemplate

    override fun push(message: Message?) {
        message ?: return
        val uid = message.receiver
        val deviceToken: String? = keyValueRedisTemplate.getDeviceToken(uid)
        if (deviceToken != null) {
            apnsService.push(message, deviceToken)
            return
        }
        signalRedisTemplate.push(message)
    }
}

