package com.chuzi.kim.component.predicate

import com.chuzi.imsdk.server.handshake.HandshakeEvent
import com.chuzi.kim.service.AccessTokenService
import jakarta.annotation.Resource
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Predicate

@Component
class HandshakePredicate : Predicate<HandshakeEvent?> {

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    override fun test(event: HandshakeEvent?): Boolean {
        return true
        event ?: return false
        val token: String = event.getHeader("token")?:return false
        LOGGER.info("token is： {}",token)
        return accessTokenService.verifyToken(token)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(HandshakePredicate::class.java)
    }

}

