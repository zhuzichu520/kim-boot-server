package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.UID
import com.chuzi.kim.component.push.DefaultMessagePusher
import com.chuzi.kim.constants.MessageAction
import com.chuzi.kim.mvc.request.WebrtcRequest
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.imsdk.server.model.Message
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/webrtc")
@Tag(name = "/webrtc", description = "单人通话信令推送接口")
class WebrtcController {

    @Resource
    private lateinit var defaultMessagePusher: DefaultMessagePusher

    @Operation(method = "POST", description = "发起单人语音通话")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/voice"])
    fun voice(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_900
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "发起单人视频通话")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/video"])
    fun video(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_901
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "接受通话")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/accept"])
    fun accept(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_902
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "拒绝通话")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/reject"])
    fun reject(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_903
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "反馈正忙")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/busy"])
    fun busy(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_904
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "挂断通话")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/hangup"])
    fun hangup(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_905
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "取消呼叫")
    @Parameter(name = "targetId", description = "对方用户ID", `in` = ParameterIn.QUERY)
    @PostMapping(value = ["/cancel"])
    fun cancel(@Parameter(hidden = true) @UID uid: String?, @RequestParam targetId: String?): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_906
        message.sender = uid
        message.receiver = targetId
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "同步IceCandidate")
    @PostMapping(value = ["/transmit/ice"])
    fun ice(
        @Parameter(hidden = true) @UID uid: String?,
        @RequestBody request: WebrtcRequest
    ): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_907
        message.sender = uid
        message.content = request.content
        message.receiver = request.uid
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "同步offer")
    @PostMapping(value = ["/transmit/offer"])
    fun offer(
        @Parameter(hidden = true) @UID uid: String?,
        @RequestBody request: WebrtcRequest
    ): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_908
        message.sender = uid
        message.content = request.content
        message.receiver = request.uid
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }

    @Operation(method = "POST", description = "同步answer")
    @PostMapping(value = ["/transmit/answer"])
    fun answer(
        @Parameter(hidden = true) @UID uid: String?,
        @RequestBody request: WebrtcRequest
    ): ResponseEntity<Any> {
        val message = Message()
        message.action = MessageAction.ACTION_909
        message.sender = uid
        message.content = request.content
        message.receiver = request.uid
        defaultMessagePusher.push(message)
        return ResponseEntity.make()
    }
}

