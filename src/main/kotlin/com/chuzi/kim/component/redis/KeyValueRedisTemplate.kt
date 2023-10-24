package com.chuzi.kim.component.redis

import com.chuzi.kim.constants.Constants
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

    fun getDeviceToken(uid: String?): String? {
        return super.boundValueOps(String.format(Constants.APNS_DEVICE_TOKEN, uid)).get()
    }

    fun openApns(uid: String, deviceToken: String?) {
        super.boundValueOps(String.format(Constants.APNS_DEVICE_TOKEN, uid)).set(deviceToken!!)
    }

    fun closeApns(uid: String) {
        super.delete(String.format(Constants.APNS_DEVICE_TOKEN, uid))
    }
}

