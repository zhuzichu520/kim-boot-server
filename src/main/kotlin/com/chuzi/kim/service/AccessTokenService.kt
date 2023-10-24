package com.chuzi.kim.service


interface AccessTokenService {

    fun generate(uid: String?): String?
    fun getUid(token: String?): String?
    fun delete(token: String?)

}

