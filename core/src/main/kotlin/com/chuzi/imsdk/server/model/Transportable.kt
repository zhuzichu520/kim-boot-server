package com.chuzi.imsdk.server.model

import com.chuzi.imsdk.server.constant.DataType

interface Transportable {
    fun getBody(): ByteArray

    fun getType(): DataType
}

