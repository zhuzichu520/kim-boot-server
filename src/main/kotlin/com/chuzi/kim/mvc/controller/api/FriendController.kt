package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.LoginToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.kim.service.FriendService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/friend")
@Tag(name = "/friend", description = "好友相关接口")
@Validated
class FriendController {

    @Resource
    private lateinit var friendService: FriendService

    @Operation(method = "POST", description = "添加好友")
    @PostMapping(value = ["/addFriend"])
    @LoginToken
    fun addFriend(
        @Parameter(hidden = true) @UID uid:String,

        @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]*\$", message = "好友账号必须字母或下划线开头")
        @Length(min=4, max = 30, message = "好友账号只能在{min}~{max}位之间")
        @Parameter(description = "好友账号", example = "admin")
        @RequestParam
        friendId: String,
    ): ResponseEntity<*> {
        friendService.addFriend(uid,friendId)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "解除好友关系")
    @PostMapping(value = ["/removeFriend"])
    @LoginToken
    fun removeFriend(
        @Parameter(hidden = true) @UID uid:String,

        @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]*\$", message = "好友账号必须字母或下划线开头")
        @Length(min=4, max = 30, message = "好友账号只能在{min}~{max}位之间")
        @Parameter(description = "好友账号", example = "admin")
        @RequestParam
        friendId: String,
    ): ResponseEntity<*> {
        friendService.deleteFriend(uid,friendId)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "获取好友列表数据")
    @PostMapping(value = ["/getFriends"])
    @LoginToken
    fun getFriends(
        @Parameter(hidden = true) @UID uid:String,
    ): ResponseEntity<*> {
        val friends = friendService.getFriendsByUid(uid)
        return ResponseEntity.ok(friends)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FriendController::class.java)
    }

}
