package com.chuzi.kim.service.impl

import cn.hutool.core.util.IdUtil
import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.entity.Message
import com.chuzi.kim.entity.User
import com.chuzi.kim.repository.UserRepository
import com.chuzi.kim.service.FriendService
import com.chuzi.kim.service.MessageService
import com.chuzi.kim.service.UserService
import com.chuzi.kim.utlis.MessageUtils
import jakarta.annotation.Resource
import jakarta.transaction.Transactional
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl : UserService {

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var friendService: FriendService

    @Resource
    private lateinit var messageService: MessageService

    @Transactional
    override fun register(user: User) {
        val optUser: Optional<User> = userRepository.findOne(Example.of(User().apply { uid = user.uid }))
        if (optUser.isPresent) {
            throw BizException("账号已存在")
        }
        userRepository.save(user)
        friendService.addFriend(user.uid!!,"admin")
        val message = Message()
        message.id = IdUtil.randomUUID()
        message.content = MessageUtils.makeTextContent("欢迎使用KIM，我是您的客服小助手，有什么问题可以直接问我。")
        message.sender = "admin"
        message.receiver = user.uid
        message.type = 0
        messageService.sendMessage(message)
    }

    override fun login(uid: String, password: String): User {
        val optUser: Optional<User> = userRepository.findOne(
            Example.of(User()
                .also {
                    it.uid = uid
                    it.password = password
                })
        )
        if (optUser.isEmpty) {
            throw BizException("登录失败，账号或密码错误")
        }
        return optUser.get()
    }

    override fun getUserByUid(uid: String): User {
        val optUser: Optional<User> = userRepository.findOne(Example.of(User().also { it.uid = uid }))
        if(optUser.isEmpty){
            throw BizException("没有找到用户信息")
        }
        return optUser.get()
    }

    override fun searchUser(keyword: String): User? {
        val users = userRepository.findUsersByUidOrMobile(keyword)
        if(users.isEmpty()){
            return null
        }
        if(users.size>1){
            throw BizException("数据异常，请联系管理员")
        }
        return users[0]
    }

}
