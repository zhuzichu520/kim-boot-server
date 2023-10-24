package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType
import java.io.Serializable

class Ping : Serializable, Transportable {
    companion object {
        private const val TAG = "PING"
        private const val DATA = "PING"
        private val INSTANCE: Ping = Ping()
        fun getInstance(): Ping {
            return INSTANCE
        }
    }

    override fun toString(): String {
        return TAG
    }

    override fun getBody(): ByteArray {
        return DATA.toByteArray()
    }

    override fun getType(): DataType {
        return DataType.PING
    }
}
