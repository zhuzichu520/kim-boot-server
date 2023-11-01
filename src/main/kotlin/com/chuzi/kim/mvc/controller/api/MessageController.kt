package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.component.push.DefaultMessagePusher
import com.chuzi.imsdk.server.model.Message
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/message")
@Tag(name = "/api/message", description = "消息相关接口")
class MessageController {

    @Resource
    private lateinit var defaultMessagePusher: DefaultMessagePusher

    @Operation(method = "POST", description = "发送消息")
    @Parameters(
        Parameter(name = "sender", description = "发送者UID", `in` = ParameterIn.QUERY, required = true, example = "system"),
        Parameter(
            name = "receiver",
            description = "接收者UID",
            `in` = ParameterIn.QUERY,
            required = true,
            example = "zhuzichu"
        ),
        Parameter(name = "action", description = "消息动作", `in` = ParameterIn.QUERY, required = true, example = "2"),
        Parameter(name = "title", description = "消息标题", `in` = ParameterIn.QUERY, example = ""),
        Parameter(name = "content", description = "消息内容", `in` = ParameterIn.QUERY, example = "你好啊啊"),
        Parameter(name = "format", description = "消息格式", `in` = ParameterIn.QUERY, example = "0"),
        Parameter(name = "extra", description = "扩展字段", `in` = ParameterIn.QUERY, example = "")
    )
    @PostMapping(value = ["/send"])
    fun send(
        @RequestParam sender: String?,
        @RequestParam receiver: String?,
        @RequestParam action: String?,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) format: String?,
        @RequestParam(required = false) extra: String?
    ): ResponseEntity<Long> {
        val message = Message()
        message.sender = sender
        message.receiver = receiver
        message.action = action
        message.content = content
        message.format = format
        message.title = title
        message.extra = extra
        message.id = System.currentTimeMillis()
        defaultMessagePusher.push(message)
        return ResponseEntity.ok(message.id)
    }
}

