package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.AccessToken
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.kim.service.AccessTokenService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@Tag(name = "/user", description = "用户登录接口")
@Validated
class UserController {

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    @Operation(method = "POST", description = "模拟登录")
    @Parameters(
        Parameter(name = "telephone", description = "手机号码", `in` = ParameterIn.QUERY),
        Parameter(name = "password", description = "密码", `in` = ParameterIn.QUERY)
    )
    @PostMapping(value = ["/login"])
    fun login(@RequestParam telephone: String): ResponseEntity<*> {
        val body: MutableMap<String, Any> = HashMap()
        body["id"] = telephone.toLong()
        body["name"] = "测试用户"
        body["telephone"] = "telephone"
        val result: ResponseEntity<Map<String, Any>> = ResponseEntity()
        result.data = body
        result.token = accessTokenService.generate(telephone)
        result.timestamp = System.currentTimeMillis()
        return result
    }

    @Operation(method = "GET", description = "退出登录")
    @GetMapping(value = ["/logout"])
    fun logout(
        @Parameter(hidden = true)
        @AccessToken
        token: String?
    ): ResponseEntity<Any> {
        accessTokenService.delete(token)
        return ResponseEntity.make()
    }
}

