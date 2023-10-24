package com.chuzi.imsdk.server.acceptor

import com.chuzi.imsdk.server.acceptor.config.SocketConfig
import com.chuzi.imsdk.server.coder.protobuf.AppMessageDecoder
import com.chuzi.imsdk.server.coder.protobuf.AppMessageEncoder
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

@Sharable
class AppSocketAcceptor(config: SocketConfig) : NioSocketAcceptor(config) {

    override fun bind() {
        if (!socketConfig.isEnable) {
            return
        }
        val bootstrap: ServerBootstrap = createServerBootstrap()
        bootstrap.childHandler(object : ChannelInitializer<SocketChannel>() {
            public override fun initChannel(ch: SocketChannel) {
                ch.pipeline().addLast(AppMessageDecoder())
                ch.pipeline().addLast(AppMessageEncoder())
                ch.pipeline().addLast(loggingHandler)
                ch.pipeline().addLast(
                    IdleStateHandler(
                        socketConfig.readIdle.seconds,
                        socketConfig.writeIdle.seconds,
                        0,
                        TimeUnit.SECONDS
                    )
                )
                ch.pipeline().addLast(this@AppSocketAcceptor)
            }
        })
        val channelFuture: ChannelFuture = bootstrap.bind(socketConfig.getPort()).syncUninterruptibly()
        channelFuture.channel().newSucceededFuture().addListener {
            val logBanner = """
                
                
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *                                                                                   *
                *                                                                                   *
                *                   App Socket Server started on port {}.                        *
                *                                                                                   *
                *                                                                                   *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                
                """.trimIndent()
            logger.info(logBanner, socketConfig.getPort())
        }
        channelFuture.channel().closeFuture().addListener { this.destroy() }
    }
}

