package com.chuzi.kim.service

import com.chuzi.kim.entity.User

interface UserService {

    fun register(user: User)

    fun login(user: User):String
    fun getUserByUid(uid: String): User

}

