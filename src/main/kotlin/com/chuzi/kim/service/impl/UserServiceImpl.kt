package com.chuzi.kim.service.impl

import com.chuzi.kim.component.exception.BizException
import com.chuzi.kim.entity.User
import com.chuzi.kim.repository.UserRepository
import com.chuzi.kim.service.AccessTokenService
import com.chuzi.kim.service.UserService
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl : UserService {

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var accessTokenService: AccessTokenService

    override fun register(user: User) {
        val optUser: Optional<User> = userRepository.findOne(Example.of(User().apply { account = user.account }))
        if (optUser.isPresent) {
            throw BizException("账号已存在")
        }
        userRepository.save(user)
    }

    override fun login(user: User): String {
        val optUser: Optional<User> = userRepository.findOne(
            Example.of(User()
                .apply {
                    account = user.account
                    password = user.password
                })
        )
        if (optUser.isEmpty) {
            throw BizException("登录失败，账号或密码错误")
        }
        return accessTokenService.generate(user.account)
    }

}
