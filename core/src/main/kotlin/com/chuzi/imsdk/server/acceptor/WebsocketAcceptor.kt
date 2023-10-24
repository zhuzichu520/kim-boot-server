package com.chuzi.imsdk.server.acceptor

import com.chuzi.imsdk.server.acceptor.config.WebsocketConfig
import com.chuzi.imsdk.server.coder.json.TextMessageDecoder
import com.chuzi.imsdk.server.coder.json.TextMessageEncoder
import com.chuzi.imsdk.server.coder.protobuf.WebMessageDecoder
import com.chuzi.imsdk.server.coder.protobuf.WebMessageEncoder
import com.chuzi.imsdk.server.constant.WebsocketProtocol
import com.chuzi.imsdk.server.handler.IllegalRequestHandler
import com.chuzi.imsdk.server.handshake.HandshakeHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler
import io.netty.handler.stream.ChunkedWriteHandler
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit


@Sharable
class WebsocketAcceptor(config: WebsocketConfig) : NioSocketAcceptor(config) {
    private val config: WebsocketConfig
    private val handshakeHandler: HandshakeHandler
    private val illegalRequestHandler: ChannelHandler = IllegalRequestHandler()

    init {
        this.config = config
        handshakeHandler = HandshakeHandler(config.handshakePredicate)
    }

    override fun bind() {
        if (!config.isEnable) {
            return
        }
        val bootstrap: ServerBootstrap = createServerBootstrap()
        bootstrap.childHandler(object : ChannelInitializer<SocketChannel>() {
            public override fun initChannel(ch: SocketChannel) {
                ch.pipeline().addLast(HttpServerCodec())
                ch.pipeline().addLast(ChunkedWriteHandler())
                ch.pipeline().addLast(HttpObjectAggregator(4 * 1024))
                ch.pipeline().addLast(WebSocketServerProtocolHandler(config.path, true))
                ch.pipeline().addLast(handshakeHandler)
                if (config.protocol == WebsocketProtocol.JSON) {
                    ch.pipeline().addLast(TextMessageDecoder())
                    ch.pipeline().addLast(TextMessageEncoder())
                } else {
                    ch.pipeline().addLast(WebMessageDecoder())
                    ch.pipeline().addLast(WebMessageEncoder())
                }
                ch.pipeline()
                    .addLast(IdleStateHandler(config.readIdle.seconds, config.writeIdle.seconds, 0, TimeUnit.SECONDS))
                ch.pipeline().addLast(loggingHandler)
                ch.pipeline().addLast(this@WebsocketAcceptor)
                ch.pipeline().addLast(illegalRequestHandler)
            }
        })
        val channelFuture: ChannelFuture = bootstrap.bind(config.getPort()).syncUninterruptibly()
        channelFuture.channel().newSucceededFuture().addListener {
            if (config.protocol == WebsocketProtocol.JSON) {
                logger.info(JSON_BANNER, config.getPort())
            }
            if (config.protocol == WebsocketProtocol.PROTOBUF) {
                logger.info(PROTOBUF_BANNER, config.getPort())
            }
        }
        channelFuture.channel().closeFuture().addListener { this.destroy() }
    }

    companion object {
        private const val JSON_BANNER =
            "\n\n" + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" + "*                                                                                   *\n" + "*                                                                                   *\n" + "*              Websocket Server started on port {} for [JSON] mode.              *\n" + "*                                                                                   *\n" + "*                                                                                   *\n" + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n"
        private const val PROTOBUF_BANNER =
            "\n\n" + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" + "*                                                                                   *\n" + "*                                                                                   *\n" + "*             Websocket Server started on port {} for [protobuf] mode.           *\n" + "*                                                                                   *\n" + "*                                                                                   *\n" + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n"
    }
}

