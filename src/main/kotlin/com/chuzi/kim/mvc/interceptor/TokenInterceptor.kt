package com.chuzi.kim.mvc.interceptor

import com.chuzi.kim.annotation.AccessToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.annotation.LoginToken
import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.config.properties.KIMTokenProperties
import com.chuzi.kim.service.AccessTokenService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException

@Component
class TokenInterceptor : HandlerInterceptor {

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    @Resource
    private lateinit var tokenProperties: KIMTokenProperties

    @Throws(IOException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val method = handler.method
        if (method.isAnnotationPresent(LoginToken::class.java)) {
            val token: String? = request.getHeader(HEADER_TOKEN)
            if (token == null) {
                response.status = HttpStatus.UNAUTHORIZED.value()
                return false
            }
            val parseToken = accessTokenService.parseToken(token)
            val uid: String? = parseToken["uid"]
            if (uid == null) {
                response.status = HttpStatus.UNAUTHORIZED.value()
                return false
            }
            request.setAttribute(UID::class.java.name, uid)
            request.setAttribute(AccessToken::class.java.name, token)
            val timeOfUse: Long = System.currentTimeMillis() - (parseToken["timeStamp"] ?: "0").toLong()
            return if (timeOfUse < tokenProperties.refreshTime.toMillis()) {
                true
            } else if (timeOfUse >= tokenProperties.refreshTime.toMillis() && timeOfUse < tokenProperties.expiresTime.toMillis()) {
                response.setHeader(HEADER_TOKEN, accessTokenService.generate(uid))
                true
            } else {
                throw BizException(10010, "token已失效")
            }
        }
        return true
    }

    companion object {
        private const val HEADER_TOKEN = "access-token"
    }
}