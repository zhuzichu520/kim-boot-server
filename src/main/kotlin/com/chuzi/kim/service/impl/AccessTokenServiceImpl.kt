package com.chuzi.kim.service.impl

import com.chuzi.kim.component.redis.TokenRedisTemplate
import com.chuzi.kim.service.AccessTokenService
import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import java.util.*


@Service
class AccessTokenServiceImpl : AccessTokenService {

    @Resource
    private lateinit var tokenRedisTemplate: TokenRedisTemplate

    override fun getUid(token: String?): String? {
        return if (StringUtils.isBlank(token)) {
            null
        } else tokenRedisTemplate[token]
    }

    override fun delete(token: String?) {
        token ?: return
        tokenRedisTemplate.delete(token)
    }

    override fun generate(uid: String?): String {
        val newToken = UUID.randomUUID().toString().replace("-", "")
        tokenRedisTemplate.save(newToken, uid)
        return newToken
    }
}

