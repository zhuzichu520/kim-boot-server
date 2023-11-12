package com.chuzi.kim.repository

import com.chuzi.kim.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(rollbackFor = [Exception::class])
interface MessageRepository : JpaRepository<Message, String> {

    @Query("SELECT message FROM Message message WHERE message.id IN :ids")
    fun findMessageInIds(ids: List<String>): List<Message>

    @Query("SELECT message from Message message where (message.sender = :uid or message.receiver = :uid) and message.timestamp > :lastTimestamp")
    fun findMessageByUidAndLastTimestamp(uid: String, lastTimestamp: Long): List<Message>

}

