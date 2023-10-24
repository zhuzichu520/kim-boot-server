package com.chuzi.kim.config.properties

import com.chuzi.imsdk.server.constant.WebsocketProtocol
import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "kim.websocket")
class KIMWebsocketProperties {
    var isEnable = false
    var port: Int? = null
    var path: String? = null
    var protocol: WebsocketProtocol? = null

    var writeIdle: Duration = Duration.ofSeconds(45)
        set(writeIdle) {
            if (writeIdle.seconds <= 0) {
                return
            }
            field = writeIdle
        }

    var readIdle: Duration = Duration.ofSeconds(60)
        set(readIdle) {
            if (readIdle.seconds <= 0) {
                return
            }
            field = readIdle
        }

    var maxPongTimeout = 1
        set(maxPongTimeout) {
            if (maxPongTimeout <= 0) {
                return
            }
            field = maxPongTimeout
        }
}

