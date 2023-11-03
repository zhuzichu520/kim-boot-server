package com.chuzi.kim.component.exception

import com.auth0.jwt.exceptions.JWTVerificationException
import com.chuzi.kim.mvc.response.ResponseEntity
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
@ResponseBody
class BizExceptionHandler {

    @ExceptionHandler(value = [BizException::class])
    fun bizExceptionHandler(e: BizException): ResponseEntity<*> {
        LOGGER.error("发生业务异常！ msg: -> ", e)
        return ResponseEntity.make<Any>(e.code, e.message)
    }

    @ExceptionHandler(value = [NullPointerException::class])
    fun exceptionHandler(e: NullPointerException): ResponseEntity<*> {
        LOGGER.error("发生空指针异常！ msg: -> ", e)
        return ResponseEntity.make<Any>(500, "服务器异常，请联系管理员")
    }

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<*> {
        LOGGER.error("服务器异常！ msg: -> ", e)
        return ResponseEntity.make<Any>(500, "服务器异常，请联系管理员")
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun violationExceptionHandler(e: ConstraintViolationException): ResponseEntity<*> {
        LOGGER.error("请求参数！ msg: -> ", e)
        return ResponseEntity.make<Any>(400,e.constraintViolations.first().message)
    }

    @ExceptionHandler(value = [JWTVerificationException::class])
    fun jwtVerificationExceptionHandler(e: JWTVerificationException): ResponseEntity<*> {
        return ResponseEntity.make<Any>(10010, e.message)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(BizExceptionHandler::class.java)
    }

}