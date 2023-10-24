package com.chuzi.imsdk.server.acceptor

import com.chuzi.imsdk.server.acceptor.config.SocketConfig
import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.constant.KIMConstant
import com.chuzi.imsdk.server.handler.LoggingHandler
import com.chuzi.imsdk.server.model.Ping
import com.chuzi.imsdk.server.model.SentBody
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.timeout.IdleState
import io.netty.handler.timeout.IdleStateEvent
import java.util.*
import java.util.concurrent.ThreadFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory


abstract class NioSocketAcceptor protected constructor(socketConfig: SocketConfig) :
    SimpleChannelInboundHandler<SentBody>() {
    protected val logger: Logger = LoggerFactory.getLogger(javaClass)
    protected val loggingHandler: ChannelHandler = LoggingHandler()
    protected val socketConfig: SocketConfig
    private var bossGroup: EventLoopGroup? = null
    private var workerGroup: EventLoopGroup? = null

    init {
        this.socketConfig = socketConfig
        val bossThreadFactory = ThreadFactory { r: Runnable? ->
            val thread = Thread(r)
            thread.name = "nio-boss-" + thread.id
            thread
        }
        val workerThreadFactory = ThreadFactory { r: Runnable? ->
            val thread = Thread(r)
            thread.name = "nio-worker-" + thread.id
            thread
        }
        if (isLinuxSystem) {
            bossGroup = EpollEventLoopGroup(bossThreadFactory)
            workerGroup = EpollEventLoopGroup(workerThreadFactory)
        } else {
            bossGroup = NioEventLoopGroup(bossThreadFactory)
            workerGroup = NioEventLoopGroup(workerThreadFactory)
        }
    }

    /**
     * 执行启动SOCKET服务
     */
    abstract fun bind()

    /**
     * 执行注销SOCKET服务
     */
    fun destroy() {
        bossGroup?.let {
            if (!it.isShuttingDown && !it.isShutdown) {
                try {
                    it.shutdownGracefully()
                } catch (ignore: Exception) {
                }
            }
        }
        workerGroup?.let {
            if (!it.isShuttingDown && !it.isShutdown) {
                try {
                    it.shutdownGracefully()
                } catch (ignore: Exception) {
                }
            }
        }
    }

    protected fun createServerBootstrap(): ServerBootstrap {
        val bootstrap = ServerBootstrap()
        bootstrap.group(bossGroup, workerGroup)
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true)
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
        bootstrap.channel(if (isLinuxSystem) EpollServerSocketChannel::class.java else NioServerSocketChannel::class.java)
        return bootstrap
    }

    override fun channelRead0(ctx: ChannelHandlerContext, body: SentBody) {
        /*
		 * 由业务层去处理其他的sentBody
		 */
        if (socketConfig.outerRequestHandler != null) {
            socketConfig.outerRequestHandler?.process(ctx.channel(), body)
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.channel().attr(ChannelAttr.ID).set(ctx.channel().id().asShortText())
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        if (ctx.channel().attr(ChannelAttr.UID) == null) {
            return
        }
        if (socketConfig.outerRequestHandler == null) {
            return
        }
        val body = SentBody()
        body.key = KIMConstant.CLIENT_CONNECT_CLOSED
        socketConfig.outerRequestHandler?.process(ctx.channel(), body)
    }

    override fun userEventTriggered(ctx: ChannelHandlerContext, evt: Any) {
        if (evt !is IdleStateEvent) {
            return
        }
        val uid = ctx.channel().attr(ChannelAttr.UID).get()
        if (evt.state() == IdleState.WRITER_IDLE && uid == null) {
            ctx.close()
            return
        }
        if (evt.state() == IdleState.WRITER_IDLE && uid != null) {
            val pingCount = ctx.channel().attr(ChannelAttr.PING_COUNT).get()
            ctx.channel().attr(ChannelAttr.PING_COUNT).set(if (pingCount == null) 1 else pingCount + 1)
            ctx.channel().writeAndFlush(Ping.getInstance())
            return
        }
        val pingCount = ctx.channel().attr(ChannelAttr.PING_COUNT).get()
        if (evt.state() == IdleState.READER_IDLE && pingCount != null && pingCount >= socketConfig.maxPongTimeout) {
            ctx.close()
            logger.info("{} pong timeout.", ctx.channel())
        }
    }

    private val isLinuxSystem: Boolean
        get() {
            val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
            return osName.contains("linux")
        }
}

