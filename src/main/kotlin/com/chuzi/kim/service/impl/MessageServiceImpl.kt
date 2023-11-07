package com.chuzi.kim.service.impl

import com.chuzi.imsdk.server.model.MessageModel
import com.chuzi.kim.component.push.DefaultMessagePusher
import com.chuzi.kim.entity.Message
import com.chuzi.kim.repository.MessageRepository
import com.chuzi.kim.service.MessageService
import jakarta.annotation.Resource
import org.springframework.stereotype.Service


@Service
class MessageServiceImpl : MessageService {

    @Resource
    private lateinit var messageRepository: MessageRepository


    @Resource
    private lateinit var defaultMessagePusher: DefaultMessagePusher

    override fun sendMessage(message: Message) : Message {
        val model = MessageModel()
        model.id = message.id
        model.scene = message.scene
        model.type = message.type
        model.subType = message.subType
        model.title = message.title
        model.content = message.content
        model.sender = message.sender
        model.receiver = message.receiver
        model.extra = message.extra
        model.timestamp = message.timestamp
        defaultMessagePusher.push(model)
        return messageRepository.save(message)
    }

}
