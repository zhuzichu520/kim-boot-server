package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType
import java.io.Serializable

class PingModel : Serializable, Transportable {
    companion object {
        private const val TAG = "PING"
        private const val DATA = "PING"
        private val INSTANCE: PingModel = PingModel()
        fun getInstance(): PingModel {
            return INSTANCE
        }
    }

    override fun toString(): String {
        return TAG
    }

    override fun getBody(): ByteArray {
        return DATA.toByteArray()
    }

    override fun getDataType(): DataType {
        return DataType.PING
    }
}
