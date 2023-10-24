package com.chuzi.kim.repository

import com.chuzi.kim.entity.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(rollbackFor = [Exception::class])
interface SessionRepository : JpaRepository<Session, Long> {
    @Modifying
    @Query("delete from Session where host = ?1 ")
    fun deleteAll(host: String?)

    @Modifying
    @Query("update Session set state = :state where id = :id")
    fun updateState(id: Long, state: Int)

    @Modifying
    @Query("update Session set state = ${Session.STATE_APNS} where uid = ?1 and channel = ?2")
    fun openApns(uid: String?, channel: String?)

    @Modifying
    @Query("update Session set state =  ${Session.STATE_ACTIVE}  where uid = ?1 and channel = ?2")
    fun closeApns(uid: String?, channel: String?)
}

