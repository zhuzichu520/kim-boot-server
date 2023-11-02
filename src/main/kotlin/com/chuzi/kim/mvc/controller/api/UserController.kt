package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.LoginToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.entity.User
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.kim.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@Tag(name = "/user", description = "用户登录接口")
@Validated
class UserController {

    @Resource
    private lateinit var userService: UserService

    @Operation(method = "POST", description = "用户注册")
    @PostMapping(value = ["/register"])
    fun register(
        @NotNull(message = "账号不能为空")
        @Length(min=4, max = 30, message = "用户名只能在4~30位之间")
        @Parameter(description = "用户账号", example = "18229858146")
        @RequestParam
        uid: String,

        @NotNull(message = "密码不能为空")
        @Length(min = 4, max = 30, message = "密码只能在4~30位之间")
        @Parameter(description = "用户密码", example = "123456")
        @RequestParam
        password: String
    ): ResponseEntity<*> {
        val user = User()
        user.uid = uid
        user.password = password
        userService.register(user)
        return ResponseEntity.make()
    }


    @Operation(method = "POST", description = "登录")
    @PostMapping(value = ["/login"])
    fun login(
        @NotNull(message = "账号不能为空")
        @Length(min=4, max = 30, message = "用户名只能在4~30位之间")
        @Parameter(description = "用户账号", example = "18229858146")
        @RequestParam
        uid: String,

        @NotNull(message = "密码不能为空")
        @Length(min = 4, max = 30, message = "密码只能在4~30位之间")
        @Parameter(description = "用户密码", example = "123456")
        @RequestParam
        password: String
    ): ResponseEntity<*> {
        val user = User()
        user.uid = uid
        user.password = password
        val token =  userService.login(user)
        val result: ResponseEntity<Map<String, Any>> = ResponseEntity()
        result.token = token
        result.timestamp = System.currentTimeMillis()
        return result
    }

    @Operation(method = "POST", description = "获取个人信息")
    @PostMapping(value = ["/profile"])
    @LoginToken
    fun profile(
        @Parameter(hidden = true) @UID uid:String
    ): ResponseEntity<*> {
        val user:User = userService.getUserByUid(uid)
        return ResponseEntity.ok(user)
    }

    @Operation(method = "GET", description = "退出登录")
    @LoginToken
    @GetMapping(value = ["/logout"])
    fun logout(
        @Parameter(hidden = true)
        token: String?
    ): ResponseEntity<Any> {
        return ResponseEntity.make()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

}
