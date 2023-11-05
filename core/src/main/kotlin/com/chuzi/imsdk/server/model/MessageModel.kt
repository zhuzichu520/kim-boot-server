package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType
import com.chuzi.imsdk.server.model.proto.MessageProto
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class MessageModel(
    var id: String? = null,
    var scene: Int = 0,
    var type: Int = 0,
    var subType: Int = 0,
    var title: String? = null,
    var content: String? = null,
    var sender: String? = null,
    var receiver: String? = null,
    var extra: String? = null,
    var timestamp: Long = System.currentTimeMillis()
) : Transportable, Serializable {
    override fun getBody(): ByteArray {
        val builder: MessageProto.Message.Builder = MessageProto.Message.newBuilder()
        builder.setId(id)
        builder.setScene(scene)
        builder.setType(type)
        builder.setSender(sender)
        builder.setTimestamp(timestamp)
        builder.setSubType(subType)
        builder.setReceiver(receiver)
        if (content != null) {
            builder.setContent(content)
        }
        if (title != null) {
            builder.setTitle(title)
        }
        if (extra != null) {
            builder.setExtra(extra)
        }
        return builder.build().toByteArray()
    }

    @JsonIgnore
    override fun getDataType(): DataType {
        return DataType.MESSAGE
    }
}
