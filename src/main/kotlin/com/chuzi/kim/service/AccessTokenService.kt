package com.chuzi.kim.service

interface AccessTokenService {

    fun generate(uid: String?): String
    fun parseToken(token: String): Map<String, String?>
    fun verifyToken(token: String): Boolean
    fun getUID(token: String):String?
}