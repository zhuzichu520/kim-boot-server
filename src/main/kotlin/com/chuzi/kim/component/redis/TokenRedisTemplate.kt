package com.chuzi.kim.component.redis

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


@Component
class TokenRedisTemplate(connectionFactory: RedisConnectionFactory) : StringRedisTemplate(connectionFactory) {

    fun save(token: String, uid: String) {
        val key = String.format(TOKEN_CACHE_PREFIX, token)
        super.boundValueOps(key).set(uid)
    }

    operator fun get(token: String): String? {
        val key = String.format(TOKEN_CACHE_PREFIX, token)
        return super.boundValueOps(key).get()
    }

    fun remove(token: String) {
        val key = String.format(TOKEN_CACHE_PREFIX, token)
        super.delete(key)
    }

    companion object {
        private const val TOKEN_CACHE_PREFIX = "TOKEN_%s"
    }

}

