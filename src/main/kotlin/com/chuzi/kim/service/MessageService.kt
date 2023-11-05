package com.chuzi.kim.service

import com.chuzi.kim.entity.Message

interface MessageService {

    fun sendMessage(message:Message) : Message

}

