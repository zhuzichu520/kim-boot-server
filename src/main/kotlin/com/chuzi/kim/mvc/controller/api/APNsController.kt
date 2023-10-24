package com.chuzi.kim.mvc.controller.api

import com.chuzi.kim.service.SessionService
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
@RequestMapping("/apns")
@Tag(name = "/apns", description = "APNs推送相关")
class APNsController {

    @Resource
    private lateinit var sessionService: SessionService

    @Operation(method = "POST", description = "开启apns")
    @Parameters(
        Parameter(
            name = "deviceToken",
            description = "APNs的deviceToken",
            `in` = ParameterIn.QUERY,
            required = true,
            example = ""
        ),
        Parameter(name = "uid", description = "用户ID", `in` = ParameterIn.QUERY, example = "0")
    )
    @PostMapping(value = ["/open"])
    fun open(@RequestParam uid: String?, @RequestParam deviceToken: String?): ResponseEntity<Any> {
        sessionService.openApns(uid, deviceToken)
        return ResponseEntity.ok().build()
    }

    @Operation(method = "POST", description = "关闭apns")
    @Parameters(
        Parameter(name = "uid", description = "用户ID", `in` = ParameterIn.QUERY, example = "0")
    )
    @PostMapping(value = ["/close"])
    fun close(@RequestParam uid: String?): ResponseEntity<Any> {
        sessionService.closeApns(uid)
        return ResponseEntity.ok().build()
    }
}

