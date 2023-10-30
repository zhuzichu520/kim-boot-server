package com.chuzi.kim.component.predicate

import com.chuzi.imsdk.server.handshake.HandshakeEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Predicate

@Component
class HandshakePredicate : Predicate<HandshakeEvent?> {
    /**
     * 验证身份信息，本方法切勿进行耗时操作！！！
     * @param event
     * @return true验证通过 false验证失败
     */
    override fun test(event: HandshakeEvent?): Boolean {
        event ?: return false
        val token: String = event.getHeader("token")?:""
        LOGGER.info("token is： {}", token)
        return true
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(HandshakePredicate::class.java)
    }

}

