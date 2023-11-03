package com.chuzi.kim.repository

import com.chuzi.kim.entity.Friend
import com.chuzi.kim.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(rollbackFor = [Exception::class])
interface FriendRepository : JpaRepository<Friend, String>{

    @Query("select user from Friend friend left join User user where friend.uid = :uid")
    fun findAllFriendByUid(uid:String):List<User>
    
}

