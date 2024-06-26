package com.chuzi.kim.repository

import com.chuzi.kim.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(rollbackFor = [Exception::class])
interface UserRepository : JpaRepository<User, String>{

    @Query("select user from User user where user.uid = :keyword or user.mobile = :keyword")
    fun findUsersByUidOrMobile(keyword: String):List<User>

}

