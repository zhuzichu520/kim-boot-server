package com.chuzi.kim.utlis

object MessageUtils {

    fun makeTextContent(text: String): String {
        val body: MutableMap<String, Any> = HashMap()
        body["msg"] = text
        return JSONUtils.toJSONString(body)
    }

}