package com.chuzi.kim.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kim.apns")
class APNsProperties {

    var isDebug = false

    var appId: String? = null

    val p12File: String?
        get() = p12.file

    val p12Password: String?
        get() = p12.password

    val p12: P12 = P12()

    class P12 {
        var file: String? = null
        var password: String? = null
    }

}

