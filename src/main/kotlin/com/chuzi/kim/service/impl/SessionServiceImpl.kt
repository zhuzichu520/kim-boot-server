package com.chuzi.kim.service.impl

import com.chuzi.kim.component.redis.KeyValueRedisTemplate
import com.chuzi.kim.entity.Session
import com.chuzi.kim.repository.SessionRepository
import com.chuzi.kim.service.SessionService
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import java.net.InetAddress

@Service
class SessionServiceImpl : SessionService {

    @Resource
    private lateinit var sessionRepository: SessionRepository

    @Resource
    private lateinit var keyValueRedisTemplate: KeyValueRedisTemplate

    private var host: String = InetAddress.getLocalHost().hostAddress

    override fun add(session: Session) {
        session.bindTime = System.currentTimeMillis()
        session.host = host
        sessionRepository.save(session)
    }

    override fun delete(id: String) {
        sessionRepository.deleteById(id)
    }

    override fun deleteLocalhost() {
        sessionRepository.deleteAll(host)
    }

    override fun updateState(id: String, state: Int) {
        sessionRepository.updateState(id, state)
    }

    override fun openApns(uid: String, deviceToken: String) {
        keyValueRedisTemplate.openApns(uid, deviceToken)
        sessionRepository.openApns(uid, Session.CHANNEL_IOS)
    }

    override fun closeApns(uid: String) {
        keyValueRedisTemplate.closeApns(uid)
        sessionRepository.closeApns(uid, Session.CHANNEL_IOS)
    }

    override fun findAll(): List<Session> {
        return sessionRepository.findAll().filter {
            it?.state == Session.STATE_ACTIVE || it?.state == Session.STATE_APNS
        }
    }
}
