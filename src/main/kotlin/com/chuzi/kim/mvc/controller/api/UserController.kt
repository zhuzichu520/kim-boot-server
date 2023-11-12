package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.LoginToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.constants.RegexConstants
import com.chuzi.kim.entity.User
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.kim.service.AccessTokenService
import com.chuzi.kim.service.FriendService
import com.chuzi.kim.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "/user", description = "用户相关接口")
@Validated
class UserController {

    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var friendService: FriendService

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    @Operation(method = "POST", description = "用户注册")
    @PostMapping(value = ["/register"])
    fun register(
        @Length(min = 4, max = 30, message = "用户名只能在4~30位之间")
        @Parameter(description = "用户账号", required = true, example = "admin")
        @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]*\$", message = "账号必须字母或下划线开头")
        @RequestParam
        uid: String,

        @Length(min = 4, max = 30, message = "密码只能在4~30位之间")
        @Parameter(description = "用户密码", required = true, example = "123456")
        @RequestParam
        password: String,

        @Length(min = 4, max = 30, message = "确认密码密码只能在4~30位之间")
        @Parameter(description = "用户确认密码", required = true, example = "123456")
        @RequestParam
        confirmPassword: String,

        @Length(min = 1, max = 30, message = "姓名只能在1~30位之间")
        @Parameter(description = "用户名称", required = true, example = "123456")
        @RequestParam
        name: String,

        @Parameter(description = "手机号", required = false, example = "18200000000")
        @Pattern(regexp = RegexConstants.REGEX_MOBILE_SIMPLE, message = "请输入格式正确的手机号")
        @RequestParam
        mobile: String?,

        @Parameter(description = "邮箱", required = false, example = "zhuzichu520@outlook.com")
        @Pattern(regexp = RegexConstants.REGEX_EMAIL, message = "请输入格式正确的邮箱")
        @RequestParam
        email: String?,

        @Parameter(description = "出生日期（13位时间戳）", required = false, example = "1600000000000")
        @RequestParam
        birthday: Long?,

        @Parameter(description = "头像地址", required = false, example = "https://zhu-zichu.gitee.io/default.png")
        @RequestParam
        avatar: String?,

        @Parameter(description = "性别", required = false, example = "0")
        @RequestParam
        gender: Int?,

        @Parameter(description = "个性签名", required = false, example = "天道酬勤！")
        @RequestParam
        signature: String?,
    ): ResponseEntity<*> {
        if (password != confirmPassword) {
            throw BizException("两次输入密码不一致")
        }
        val user = User()
        user.uid = uid
        user.password = password
        user.name = name
        user.mobile = mobile
        user.email = email
        user.birthday = birthday
        user.avatar = avatar
        user.password = password
        user.gender = gender
        user.signature = signature
        userService.register(user)
        return ResponseEntity.make()
    }


    @Operation(method = "POST", description = "登录")
    @PostMapping(value = ["/login"])
    fun login(
        @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]*\$", message = "账号必须字母或下划线开头")
        @Length(min = 4, max = 30, message = "用户名只能在{min}~{max}位之间")
        @Parameter(description = "用户账号", example = "admin")
        @RequestParam
        uid: String,

        @Length(min = 4, max = 30, message = "密码只能在{min}~{max}位之间")
        @Parameter(description = "用户密码", example = "123456")
        @RequestParam
        password: String
    ): ResponseEntity<*> {
        val user = userService.login(uid, password)
        user.password = null
        val result: ResponseEntity<User> = ResponseEntity()
        result.token = accessTokenService.generate(uid)
        result.data = user
        result.timestamp = System.currentTimeMillis()
        return result
    }

    @Operation(method = "POST", description = "获取个人信息")
    @PostMapping(value = ["/profile"])
    @LoginToken
    fun profile(
        @Parameter(hidden = true) @UID uid: String
    ): ResponseEntity<*> {
        val user: User = userService.getUserByUid(uid)
        return ResponseEntity.ok(user)
    }

    @Operation(method = "POST", description = "获取用户信息")
    @PostMapping(value = ["/getUserInfo"])
    @LoginToken
    fun getUserInfo(
        @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]*\$", message = "账号必须字母或下划线开头")
        @Length(min = 4, max = 30, message = "用户名只能在{min}~{max}位之间")
        @Parameter(description = "用户id", example = "sunuwkong")
        @RequestParam
        uid: String,
    ): ResponseEntity<*> {
        val user: User = userService.getUserByUid(uid)
        user.password = null
        return ResponseEntity.ok(user)
    }

    @Operation(method = "POST", description = "根据关键字搜索用户")
    @PostMapping(value = ["/searchUser"])
    @LoginToken
    fun searchUser(
        @Parameter(hidden = true) @UID uid: String,

        @NotNull(message = "搜索关键字不能为空")
        @Length(max = 30, message = "搜索长度限制{max}")
        @Parameter(description = "关键字", example = "孙悟空")
        @RequestParam
        keyword: String,
    ): ResponseEntity<*> {
        val user: User = userService.searchUser(keyword) ?: return ResponseEntity.make()
        val data = mutableMapOf<String, Any>()
        data["userInfo"] = user
        data["isFriend"] = friendService.isFriend(uid, user.uid)
        return ResponseEntity.ok(data)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

}
