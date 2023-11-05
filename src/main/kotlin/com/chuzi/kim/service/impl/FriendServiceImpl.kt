package com.chuzi.kim.service.impl

import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.entity.Friend
import com.chuzi.kim.entity.User
import com.chuzi.kim.repository.FriendRepository
import com.chuzi.kim.service.FriendService
import jakarta.annotation.Resource
import jakarta.transaction.Transactional
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class FriendServiceImpl : FriendService {

    @Resource
    private lateinit var friendRepository: FriendRepository


    @Transactional
    override fun addFriend(uid: String, friendId: String) {
        if(isFriend(uid,friendId)){
            throw BizException("你们已经是好友关系")
        }
        friendRepository.saveAll(listOf(Friend(uid=uid, friendId = friendId),Friend(uid=friendId, friendId = uid)))
    }

    @Transactional
    override fun deleteFriend(uid: String, friendId: String) {
        val userFriend = Friend().also {
            it.uid = uid
            it.friendId = friendId
        }
        val friendUser = Friend().also {
            it.uid = friendId
            it.friendId = uid
        }
        val optUserFriend = friendRepository.findOne(Example.of(userFriend))
        val optFriendUser = friendRepository.findOne(Example.of(friendUser))
        if(!(optUserFriend.isPresent && optFriendUser.isPresent)){
            throw BizException("你们已经不是好友关系")
        }
        friendRepository.deleteAll(listOf(optUserFriend.get(),optFriendUser.get()))
    }

    override fun isFriend(uid: String, friendId: String?): Boolean {
        if(friendId == null)
            return false
        if(uid == friendId)
            return true
        val userFriend = Friend().also {
            it.uid = uid
            it.friendId = friendId
        }
        val friendUser = Friend().also {
            it.uid = friendId
            it.friendId = uid
        }
        return friendRepository.findOne(Example.of(userFriend)).isPresent && friendRepository.findOne(Example.of(friendUser)).isPresent
    }

    override fun getFriendsByUid(uid:String): List<User> {
        return friendRepository.findAllFriendByUid(uid)
    }

}
