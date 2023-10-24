package com.chuzi.kim.utlis

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.json.JsonReadFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.json.JsonMapper
import java.io.IOException

object JSONUtils {

    private val OBJECT_MAPPER = JsonMapper.builder()
        .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
        .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .build()

    fun toJSONString(data: Any?): String {
        return try {
            OBJECT_MAPPER.writeValueAsString(data)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException(e)
        }
    }

    fun <T> fromJson(str: String?, clazz: Class<T>?): T {
        return try {
            OBJECT_MAPPER.readValue(str, clazz)
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }
    }

    fun <T> fromJson(byteArray: ByteArray?, clazz: Class<T>?): T {
        return try {
            OBJECT_MAPPER.readValue(byteArray, clazz)
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }
    }

    fun <T> parseList(str: String?, clazz: Class<T>?): List<T> {
        val javaType: JavaType = OBJECT_MAPPER.typeFactory.constructCollectionLikeType(MutableList::class.java, clazz)
        return try {
            OBJECT_MAPPER.readValue(str, javaType)
        } catch (e: IOException) {
            throw IllegalArgumentException(e)
        }
    }

}