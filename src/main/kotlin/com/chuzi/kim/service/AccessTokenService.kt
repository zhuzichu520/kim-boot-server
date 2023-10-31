package com.chuzi.kim.service

interface AccessTokenService {

    fun generate(account: String?): String
    fun parseToken(token: String): Map<String, String?>
    fun verifyToken(token: String): Boolean
}