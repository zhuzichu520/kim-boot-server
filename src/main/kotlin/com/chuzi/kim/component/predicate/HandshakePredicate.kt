package com.chuzi.kim.component.predicate

import com.chuzi.imsdk.server.handshake.HandshakeEvent
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
        /*
            可通过header或者uri传递参数
            String token = event.getHeader("token");
            String token = event.getParameter("token");
            do auth....
         */
        return true
    }
}

