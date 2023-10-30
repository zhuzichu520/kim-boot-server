package com.chuzi.kim.service

import com.chuzi.kim.entity.Session

interface SessionService {

    fun add(session: Session)
    fun delete(id: Long)
    fun deleteLocalhost()
    fun updateState(id: Long, state: Int)
    fun openApns(uid: String, deviceToken: String)
    fun closeApns(uid: String)
    fun findAll(): List<Session>

}

