package com.chuzi.kim.service

import com.chuzi.kim.entity.User

interface UserService {

    fun register(user: User)

    fun login(uid: String, password: String): User
    fun getUserByUid(uid: String): User

    fun searchUser(keyword: String): User?
}

