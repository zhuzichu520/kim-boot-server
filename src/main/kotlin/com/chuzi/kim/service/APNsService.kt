package com.chuzi.kim.service

import com.chuzi.imsdk.server.model.Message

interface APNsService {

    fun push(message: Message, deviceToken: String)

}

