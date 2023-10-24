package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.model.proto.MessageProto
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class Message(
    var id: Long = 0,
    var action: String? = null,
    var title: String? = null,
    var content: String? = null,
    var sender: String? = null,
    var receiver: String? = null,
    var format: String? = null,
    var extra: String? = null,
    var timestamp: Long = System.currentTimeMillis()
) : Transportable, Serializable {
    override fun getBody(): ByteArray {
        val builder: MessageProto.Model.Builder = MessageProto.Model.newBuilder()
        builder.setId(id)
        builder.setAction(action)
        builder.setSender(sender)
        builder.setTimestamp(timestamp)
        if (receiver != null) {
            builder.setReceiver(receiver)
        }
        if (content != null) {
            builder.setContent(content)
        }
        if (title != null) {
            builder.setTitle(title)
        }
        if (extra != null) {
            builder.setExtra(extra)
        }
        if (format != null) {
            builder.setFormat(format)
        }
        return builder.build().toByteArray()
    }

    @JsonIgnore
    override fun getType(): DataType {
        return DataType.MESSAGE
    }
}
