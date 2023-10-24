package com.chuzi.kim.component.redis

import com.chuzi.kim.component.event.MessageEvent
import com.chuzi.kim.component.event.SessionEvent
import com.chuzi.kim.constants.Constants
import com.chuzi.kim.entity.Session
import com.chuzi.kim.utlis.JSONUtils
import com.chuzi.imsdk.server.model.Message
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


@Component
class SignalRedisTemplate(connectionFactory: LettuceConnectionFactory) : StringRedisTemplate(connectionFactory) {
    @Value("\${spring.profiles.active}")
    private val env: String? = null

    @Resource
    private lateinit var applicationContext: ApplicationContext

    init {
        connectionFactory.validateConnection = true
    }

    fun push(message: Message?) {
        if (isDev && message !== null) {
            applicationContext.publishEvent(MessageEvent(message))
            return
        }
        super.convertAndSend(Constants.PUSH_MESSAGE_INNER_QUEUE, JSONUtils.toJSONString(message))
    }

    fun bind(session: Session?) {
        if (isDev && session !== null) {
            applicationContext.publishEvent(SessionEvent(session))
            return
        }
        super.convertAndSend(Constants.BIND_MESSAGE_INNER_QUEUE, JSONUtils.toJSONString(session))
    }

    private val isDev: Boolean
        get() = "dev" == env
}

