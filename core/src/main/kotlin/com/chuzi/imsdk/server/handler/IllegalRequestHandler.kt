package com.chuzi.imsdk.server.handler

import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.handler.codec.http.HttpVersion
import org.slf4j.LoggerFactory


/**
 * 非法请求处理
 */
@Sharable
class IllegalRequestHandler : SimpleChannelInboundHandler<FullHttpRequest>() {
    public override fun channelRead0(ctx: ChannelHandlerContext, request: FullHttpRequest) {
        val path = request.uri()
        LOGGER.warn("收到无效的请求地址,path:{} header:{}", path, request.headers())
        ctx.channel().writeAndFlush(DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN)).addListener(ChannelFutureListener.CLOSE)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IllegalRequestHandler::class.java)
    }
}