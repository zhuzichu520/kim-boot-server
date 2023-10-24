package com.chuzi.kim.component.event

import com.chuzi.kim.entity.Session
import org.springframework.context.ApplicationEvent

class SessionEvent(session: Session) : ApplicationEvent(session) {

    override fun getSource(): Session {
        return source as Session
    }

}

