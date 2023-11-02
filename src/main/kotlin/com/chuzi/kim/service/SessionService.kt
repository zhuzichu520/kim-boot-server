package com.chuzi.kim.service

import com.chuzi.kim.entity.Session

interface SessionService {

    fun add(session: Session)
    fun delete(id: String)
    fun deleteLocalhost()
    fun updateState(id: String, state: Int)
    fun openApns(uid: String, deviceToken: String)
    fun closeApns(uid: String)
    fun findAll(): List<Session>

}

