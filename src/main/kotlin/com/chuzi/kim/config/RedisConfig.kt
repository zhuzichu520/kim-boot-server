package com.chuzi.kim.config

import com.chuzi.kim.constants.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer


@Configuration
@AutoConfigureBefore(RedisAutoConfiguration::class)
class RedisConfig @Autowired constructor(
    connectionFactory: LettuceConnectionFactory,
    @Value("\${spring.profiles.active}") profile: String?
) {

    init {
        if ("dev" == profile) {
            connectionFactory.validateConnection = true
        }
    }

    @Bean
    fun redisMessageListenerContainer(
        connectionFactory: RedisConnectionFactory,
        pushMessageListener: MessageListener,
        bindMessageListener: MessageListener
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(pushMessageListener, ChannelTopic(Constants.PUSH_MESSAGE_INNER_QUEUE))
        container.addMessageListener(bindMessageListener, ChannelTopic(Constants.BIND_MESSAGE_INNER_QUEUE))
        return container
    }
}