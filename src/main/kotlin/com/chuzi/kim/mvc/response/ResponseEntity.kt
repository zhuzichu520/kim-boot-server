package com.chuzi.kim.mvc.response

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

@Schema(name = "ResponseEntity", description = "统一返回结果实体对象")
data class ResponseEntity<T>(
    @Parameter(description = "错误状态码")
    var code: Int = HttpStatus.OK.value(),
    @Parameter(description = "错误信息")
    var message: String? = null,
    @Parameter(description = "结果数据")
    var data: T? = null,
    @Parameter(description = "token")
    var token: String? = null,
    @Parameter(description = "时间戳")
    var timestamp: Long? = null
) {

    companion object {
        fun make(): ResponseEntity<Any> {
            return ResponseEntity()
        }

        fun make(code: Int): ResponseEntity<Any> {
            return make(code, null)
        }

        fun <T> make(code: Int, message: String?): ResponseEntity<T> {
            val result: ResponseEntity<T> = ResponseEntity()
            result.code = code
            result.message = message
            return result
        }

        fun make(status: HttpStatus): ResponseEntity<Any> {
            val result: ResponseEntity<Any> = ResponseEntity()
            result.code = status.value()
            result.message = status.reasonPhrase
            return result
        }

        fun <Q> make(status: HttpStatus, message: String?): ResponseEntity<Q> {
            val result: ResponseEntity<Q> = ResponseEntity()
            result.code = status.value()
            result.message = message
            return result
        }

        fun <Q> ok(data: Q): ResponseEntity<Q> {
            val result: ResponseEntity<Q> = ResponseEntity()
            result.data = data
            return result
        }

    }
}