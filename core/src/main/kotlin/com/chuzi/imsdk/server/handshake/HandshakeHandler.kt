package com.chuzi.imsdk.server.handshake

import com.chuzi.imsdk.server.constant.KIMConstant
import com.chuzi.imsdk.server.model.ReplyBody
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete
import org.slf4j.LoggerFactory
import java.util.function.Predicate


@Sharable
class HandshakeHandler(handshakePredicate: Predicate<HandshakeEvent>?) : ChannelInboundHandlerAdapter() {

    private val failedBody: ReplyBody = ReplyBody.make(KIMConstant.CLIENT_HANDSHAKE,
            HttpResponseStatus.UNAUTHORIZED.code(),
            HttpResponseStatus.UNAUTHORIZED.reasonPhrase())
    private val handshakePredicate: Predicate<HandshakeEvent>?

    init {
        this.handshakePredicate = handshakePredicate
    }

    @Throws(Exception::class)
    override fun userEventTriggered(ctx: ChannelHandlerContext, evt: Any) {
        super.userEventTriggered(ctx, evt)
        if (evt is HandshakeComplete) {
            doAuthentication(ctx, evt)
        }
    }

    private fun doAuthentication(context: ChannelHandlerContext, event: HandshakeComplete) {
        if (handshakePredicate == null) {
            return
        }
        if (!handshakePredicate.test(HandshakeEvent.of(event))) {
            val  channel = context.channel()
            channel.writeAndFlush(failedBody).addListener(ChannelFutureListener {
                Thread.sleep(30)
                channel.close()
            })
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(HandshakeHandler::class.java)
    }


}