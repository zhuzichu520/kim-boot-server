package com.chuzi.kim.repository

import com.chuzi.kim.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(rollbackFor = [Exception::class])
interface MessageRepository : JpaRepository<Message, String>{


}

