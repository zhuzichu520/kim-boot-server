package com.chuzi.kim.service.impl

import com.chuzi.kim.config.properties.APNsProperties
import com.chuzi.kim.service.APNsService
import com.eatthepath.pushy.apns.ApnsClient
import com.eatthepath.pushy.apns.ApnsClientBuilder
import com.eatthepath.pushy.apns.ApnsPushNotification
import com.eatthepath.pushy.apns.PushNotificationResponse
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification
import com.eatthepath.pushy.apns.util.TokenUtil
import com.chuzi.imsdk.server.model.Message
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class APNsServiceImpl @Autowired constructor(properties: APNsProperties) : APNsService {

    private lateinit var apnsClient: ApnsClient

    private lateinit var properties: APNsProperties

    init {
        this.properties = properties
        val stream = properties.p12File?.let { javaClass.getResourceAsStream(it) }
        apnsClient = ApnsClientBuilder()
            .setApnsServer(if (properties.isDebug) ApnsClientBuilder.DEVELOPMENT_APNS_HOST else ApnsClientBuilder.PRODUCTION_APNS_HOST)
            .setClientCredentials(stream, properties.p12Password)
            .build()
    }

    override fun push(message: Message, deviceToken: String) {
        if (StringUtils.isBlank(deviceToken)) {
            return
        }
        val payloadBuilder: ApnsPayloadBuilder = SimpleApnsPayloadBuilder()
        payloadBuilder.setAlertTitle("您有一条新的消息")
        payloadBuilder.setSound("default")
        payloadBuilder.setBadgeNumber(1)
        payloadBuilder.addCustomProperty("id", message.id)
        payloadBuilder.addCustomProperty("action", message.action)
        payloadBuilder.addCustomProperty("content", message.content)
        payloadBuilder.addCustomProperty("sender", message.sender)
        payloadBuilder.addCustomProperty("receiver", message.receiver)
        payloadBuilder.addCustomProperty("format", message.format)
        payloadBuilder.addCustomProperty("extra", message.extra)
        payloadBuilder.addCustomProperty("timestamp", message.timestamp)
        val token = TokenUtil.sanitizeTokenString(deviceToken)
        val payload = payloadBuilder.build()
        val notification: ApnsPushNotification = SimpleApnsPushNotification(token, properties.appId, payload)
        apnsClient.sendNotification(notification)
            .whenComplete { response: PushNotificationResponse<ApnsPushNotification?>?, cause: Throwable? ->
                if (response != null) {
                    LOGGER.info("APNs push done.\ndeviceToken : {} \napnsPayload : {}", deviceToken, payload)
                } else {
                    LOGGER.error("APNs push failed", cause)
                }
            }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(APNsServiceImpl::class.java)
    }


}

