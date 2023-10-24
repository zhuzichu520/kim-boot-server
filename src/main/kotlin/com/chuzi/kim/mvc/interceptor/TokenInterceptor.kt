package com.chuzi.kim.mvc.interceptor

import com.chuzi.kim.annotation.AccessToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.service.AccessTokenService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException


@Component
class TokenInterceptor : HandlerInterceptor {

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    @Throws(IOException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token: String = request.getHeader(HEADER_TOKEN)
        val uid: String? = accessTokenService.getUid(token)
        if (uid == null) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            return false
        }
        request.setAttribute(UID::class.java.getName(), uid)
        request.setAttribute(AccessToken::class.java.getName(), token)
        return true
    }

    companion object {
        private const val HEADER_TOKEN = "access-token"
    }
}

