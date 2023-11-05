package com.chuzi.kim.service

import com.chuzi.kim.entity.User

interface FriendService {

    fun addFriend(uid: String,friendId:String)
    fun deleteFriend(uid: String,friendId:String)
    fun isFriend(uid: String,friendId:String?): Boolean
    fun getFriendsByUid(uid:String): List<User>

}

