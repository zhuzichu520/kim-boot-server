package com.chuzi.kim.service.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.chuzi.kim.config.properties.KIMTokenProperties
import com.chuzi.kim.service.AccessTokenService
import jakarta.annotation.Resource
import org.springframework.stereotype.Service


@Service
class AccessTokenServiceImpl : AccessTokenService {

    @Resource
    private lateinit var tokenProperties: KIMTokenProperties

    override fun generate(account: String?): String {
        return JWT.create()
            .withClaim("account", account)
            .withClaim("timeStamp",System.currentTimeMillis())
            .sign(Algorithm.HMAC256(tokenProperties.secretKey))
    }

    override fun parseToken(token: String): Map<String, String?> {
        val map = HashMap<String, String>()
        val jwt = JWT.require(Algorithm.HMAC256(tokenProperties.secretKey))
            .build().verify(token)
        val account = jwt.getClaim("userId").asString()
        val timeStamp = jwt.getClaim("timeStamp").asLong().toString()
        map["account"] = account
        map["timeStamp"] = timeStamp
        return map
    }

    override fun verifyToken(token: String): Boolean {
        return try {
            JWT.require(Algorithm.HMAC256(tokenProperties.secretKey)).build().verify(token)
            true
        }catch (e:Exception){
            false
        }
    }

}