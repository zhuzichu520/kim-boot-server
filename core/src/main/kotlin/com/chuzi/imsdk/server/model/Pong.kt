package com.chuzi.imsdk.server.model

import java.io.Serializable

class Pong : Serializable {

    companion object {
        private const val TAG = "PONG"
        private val INSTANCE: Pong = Pong()
        fun getInstance(): Pong {
            return INSTANCE
        }
    }

    override fun toString(): String {
        return TAG
    }

}