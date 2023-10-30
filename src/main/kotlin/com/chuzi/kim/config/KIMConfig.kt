package com.chuzi.kim.config

import com.chuzi.kim.component.handler.annotation.KIMHandler
import com.chuzi.kim.config.properties.KIMAppSocketProperties
import com.chuzi.kim.config.properties.KIMWebsocketProperties
import com.chuzi.kim.service.SessionService
import com.chuzi.imsdk.server.acceptor.AppSocketAcceptor
import com.chuzi.imsdk.server.acceptor.WebsocketAcceptor
import com.chuzi.imsdk.server.acceptor.config.SocketConfig
import com.chuzi.imsdk.server.acceptor.config.WebsocketConfig
import com.chuzi.imsdk.server.group.SessionGroup
import com.chuzi.imsdk.server.group.TagSessionGroup
import com.chuzi.imsdk.server.handler.KIMRequestHandler
import com.chuzi.imsdk.server.handshake.HandshakeEvent
import com.chuzi.imsdk.server.model.SentBody
import io.netty.channel.Channel
import jakarta.annotation.Resource
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Predicate


@EnableConfigurationProperties(KIMWebsocketProperties::class, KIMAppSocketProperties::class)
@Configuration
class KIMConfig : KIMRequestHandler, ApplicationListener<ApplicationStartedEvent?> {

    private val handlerMap = HashMap<String, KIMRequestHandler>()

    @Resource
    private lateinit var applicationContext: ApplicationContext

    @Resource
    private lateinit var sessionService: SessionService

    @Bean
    fun sessionGroup(): SessionGroup {
        return SessionGroup()
    }

    @Bean
    fun tagSessionGroup(): TagSessionGroup {
        return TagSessionGroup()
    }

    @Bean(destroyMethod = "destroy", initMethod = "bind")
    @ConditionalOnProperty(name = ["kim.websocket.enable"], matchIfMissing = true)
    fun websocketAcceptor(
        properties: KIMWebsocketProperties,
        handshakePredicate: Predicate<HandshakeEvent>
    ): WebsocketAcceptor {
        val config = WebsocketConfig()
        config.setPort(properties.port)
        config.handshakePredicate = handshakePredicate
        config.path = properties.path
        config.protocol = properties.protocol
        config.outerRequestHandler = this
        config.isEnable = properties.isEnable
        config.writeIdle = properties.writeIdle
        config.readIdle = properties.readIdle
        config.maxPongTimeout = properties.maxPongTimeout
        return WebsocketAcceptor(config)
    }

    @Bean(destroyMethod = "destroy", initMethod = "bind")
    @ConditionalOnProperty(name = ["kim.app.enable"], matchIfMissing = true)
    fun appSocketAcceptor(properties: KIMAppSocketProperties): AppSocketAcceptor {
        val config = SocketConfig()
        config.setPort(properties.port)
        config.outerRequestHandler = this
        config.isEnable = properties.isEnable
        config.writeIdle = properties.writeIdle
        config.readIdle = properties.readIdle
        config.maxPongTimeout = properties.maxPongTimeout
        return AppSocketAcceptor(config)
    }

    override fun process(channel: Channel, body: SentBody) {
        val handler = handlerMap[body.key] ?: return
        handler.process(channel, body)
    }

    override fun onApplicationEvent(applicationStartedEvent: ApplicationStartedEvent) {
        val beans = applicationContext.getBeansOfType(KIMRequestHandler::class.java)
        for ((_, handler) in beans) {
            val annotation: KIMHandler? = handler.javaClass.getAnnotation(KIMHandler::class.java)
            if (annotation != null) {
                handlerMap[annotation.key] = handler
            }
        }
        sessionService.deleteLocalhost()
    }
}