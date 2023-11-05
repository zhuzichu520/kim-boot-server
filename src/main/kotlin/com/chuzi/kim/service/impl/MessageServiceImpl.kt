package com.chuzi.kim.service.impl

import com.chuzi.kim.entity.Message
import com.chuzi.kim.repository.MessageRepository
import com.chuzi.kim.service.MessageService
import jakarta.annotation.Resource
import org.springframework.stereotype.Service


@Service
class MessageServiceImpl : MessageService {

    @Resource
    private lateinit var messageRepository: MessageRepository
    override fun sendMessage(message: Message) : Message {
        return messageRepository.save(message)
    }

}
