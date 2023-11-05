package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.model.proto.ReplyBodyProto
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class ReplyBodyModel(
    var key: String? = null,
    var code: String? = null,
    var message: String? = null,
    var data: Map<String, String> = HashMap(),
    var timestamp: Long = 0
) : Transportable, Serializable {
    companion object {
        fun make(key: String, code: Int, message: String): ReplyBodyModel {
            val body = ReplyBodyModel()
            body.key = key
            body.code = code.toString()
            body.message = message
            return body
        }
    }

    override fun getBody(): ByteArray {
        val builder: ReplyBodyProto.ReplyBody.Builder = ReplyBodyProto.ReplyBody.newBuilder()
        builder.setCode(code)
        if (message != null) {
            builder.setMessage(message)
        }
        if (data.isNotEmpty()) {
            builder.putAllData(data)
        }
        builder.setKey(key)
        builder.setTimestamp(timestamp)
        return builder.build().toByteArray()
    }

    @JsonIgnore
    override fun getDataType(): DataType {
        return DataType.REPLY
    }
}
