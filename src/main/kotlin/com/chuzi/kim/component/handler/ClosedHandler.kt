package com.chuzi.kim.component.handler

import com.chuzi.kim.component.handler.annotation.KIMHandler
import com.chuzi.kim.constants.Constants
import com.chuzi.kim.entity.Session
import com.chuzi.kim.service.SessionService
import com.chuzi.imsdk.server.constant.ChannelAttr
import com.chuzi.imsdk.server.handler.KIMRequestHandler
import com.chuzi.imsdk.server.model.SentBodyModel
import io.netty.channel.Channel
import jakarta.annotation.Resource

@KIMHandler(key = "client_closed")
class ClosedHandler : KIMRequestHandler {

    @Resource
    private lateinit var sessionService: SessionService

    override fun process(channel: Channel, body: SentBodyModel) {
        val sessionId = channel.attr(Constants.SESSION_ID).get() ?: return
        if (channel.attr(ChannelAttr.CHANNEL).get() == Session.CHANNEL_IOS) {
            sessionService.updateState(sessionId, Session.STATE_INACTIVE)
            return
        }
        sessionService.delete(sessionId)
    }

}

