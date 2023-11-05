package com.chuzi.imsdk.server.model

import java.io.Serializable

class PongModel : Serializable {

    companion object {
        private const val TAG = "PONG"
        private val INSTANCE: PongModel = PongModel()
        fun getInstance(): PongModel {
            return INSTANCE
        }
    }

    override fun toString(): String {
        return TAG
    }

}