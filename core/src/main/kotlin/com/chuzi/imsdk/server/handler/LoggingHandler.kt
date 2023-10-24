package com.chuzi.imsdk.server.handler

import com.chuzi.imsdk.server.constant.ChannelAttr
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

@Sharable
class LoggingHandler : LoggingHandler(LogLevel.INFO) {
    @Throws(Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        super.channelRead(ctx, msg)
        Thread.currentThread().setName(name)
    }

    @Throws(Exception::class)
    override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        super.write(ctx, msg, promise)
        Thread.currentThread().setName(name)
    }

    @Throws(Exception::class)
    override fun close(ctx: ChannelHandlerContext, promise: ChannelPromise) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        super.close(ctx, promise)
        Thread.currentThread().setName(name)
    }

    @Throws(Exception::class)
    override fun channelInactive(ctx: ChannelHandlerContext) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        super.channelInactive(ctx)
        Thread.currentThread().setName(name)
    }

    @Throws(Exception::class)
    override fun userEventTriggered(ctx: ChannelHandlerContext, evt: Any) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        super.userEventTriggered(ctx, evt)
        Thread.currentThread().setName(name)
    }

    override fun channelRegistered(ctx: ChannelHandlerContext) {
        ctx.fireChannelRegistered()
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext) {
        ctx.fireChannelUnregistered()
    }

    override fun deregister(ctx: ChannelHandlerContext, promise: ChannelPromise) {
        ctx.deregister(promise)
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.fireChannelReadComplete()
    }

    override fun flush(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    @Deprecated("Deprecated in Java")
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        val name = Thread.currentThread().name
        setThreadName(ctx)
        logger.warn(this.format(ctx, "EXCEPTION", cause), cause)
        Thread.currentThread().setName(name)
        ctx.fireExceptionCaught(cause)
    }

    private fun setThreadName(context: ChannelHandlerContext) {
        val uid = context.channel().attr<String>(ChannelAttr.UID).get()
        if (uid != null) {
            Thread.currentThread().setName("nio-uid-$uid")
        }
    }
}

