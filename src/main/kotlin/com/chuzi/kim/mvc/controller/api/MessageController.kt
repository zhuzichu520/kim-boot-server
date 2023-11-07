package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.annotation.LoginToken
import com.chuzi.kim.annotation.UID
import com.chuzi.kim.component.push.DefaultMessagePusher
import com.chuzi.kim.entity.Message
import com.chuzi.kim.mvc.response.ResponseEntity
import com.chuzi.kim.service.MessageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
@Tag(name = "/message", description = "消息相关接口")
@Validated
class MessageController {

    @Resource
    private lateinit var messageService: MessageService

    @Operation(method = "POST", description = "发送消息")
    @LoginToken
    @PostMapping(value = ["/send"])
    fun send(
        @Parameter(hidden = true) @UID uid:String,

        @Parameter( name = "id",description = "消息UUID",required = true,example = "d55ebd2d-de33-4ba3-b2e1-79f9b6173fe3")
        @RequestParam id: String,

        @Parameter( name = "receiver",description = "接收者UID",required = true,example = "zhuzichu")
        @RequestParam receiver: String,

        @Parameter( name = "scene",description = "仅可传入 0 或 1，0：单聊消息，1：群消息",required = true,example = "0")
        @RequestParam scene: Int,

        @Parameter( name = "type",description = "消息类型",required = true,example = "0")
        @RequestParam type: Int,

        @Parameter(name = "content", description = "消息内容",required = true, example = "你好啊啊")
        @RequestParam content: String,

        @Parameter(name = "subType",description = "消息子类型",required = false,example = "0")
        @RequestParam subtype: Int?,

        @Parameter(name = "title", description = "消息标题",required = false, example = "")
        @RequestParam title: String?,

        @Parameter(name = "extra", description = "扩展字段",required = false, example = "")
        @RequestParam extra: String?
    ): ResponseEntity<*> {
        var message = Message()
        message.id = id
        message.sender = uid
        message.scene = scene
        message.receiver = receiver
        message.type = type
        message.content = content
        message.subType = subtype?:0
        message.title = title
        message.extra = extra
        message = messageService.sendMessage(message)
        return ResponseEntity.ok(message)

    }
}

