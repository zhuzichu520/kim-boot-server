package com.chuzi.kim.service.impl

import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.entity.User
import com.chuzi.kim.repository.UserRepository
import com.chuzi.kim.service.UserService
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl : UserService {

    @Resource
    private lateinit var userRepository: UserRepository

    override fun register(user: User) {
        val optUser: Optional<User> = userRepository.findOne(Example.of(User().apply { account = user.account }))
        if(optUser.isPresent){
            throw BizException("账号已存在")
        }
        userRepository.save(user)
    }

}
