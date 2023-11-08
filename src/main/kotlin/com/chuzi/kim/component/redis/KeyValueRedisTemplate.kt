package com.chuzi.kim.component.redis

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class KeyValueRedisTemplate(connectionFactory: RedisConnectionFactory?) : StringRedisTemplate(connectionFactory!!) {
    operator fun set(key: String, value: String) {
        super.boundValueOps(key).set(value)
    }

    operator fun get(key: String): String? {
        return super.boundValueOps(key).get()
    }

}

