package com.chuzi.imsdk.server.model

import java.io.Serializable

data class SentBodyModel(
    var key: String? = null,
    var data: Map<String, String> = HashMap(),
    var timestamp: Long = 0
) : Serializable {

    operator fun get(key: String, defaultValue: String? = null): String {
        return data.getOrDefault(key, defaultValue ?: "")
    }

}