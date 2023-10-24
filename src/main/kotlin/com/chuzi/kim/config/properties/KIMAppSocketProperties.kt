package com.chuzi.kim.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "kim.app")
class KIMAppSocketProperties {
    var isEnable = false
    var port: Int? = null

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

