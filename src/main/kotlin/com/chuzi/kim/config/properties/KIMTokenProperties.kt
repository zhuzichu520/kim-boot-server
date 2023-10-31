package com.chuzi.kim.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Duration

@ConfigurationProperties(prefix = "kim.token")
@Component
data class KIMTokenProperties(
    var secretKey: String  = "123456789",
    var refreshTime : Duration = Duration.ofDays(1),
    var expiresTime : Duration = Duration.ofDays(5)
)
