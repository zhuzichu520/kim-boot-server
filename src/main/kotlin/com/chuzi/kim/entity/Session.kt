package com.chuzi.kim.entity

import jakarta.persistence.*


@Entity
@Table(name = "t_im_session")
data class Session(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    var id: Long? = null,

    @Column(name = "uid")
    var uid: String? = null,

    @Column(name = "nid", length = 32, nullable = false)
    var nid: String? = null,

    @Column(name = "device_id", length = 64, nullable = false)
    var deviceId: String? = null,

    @Column(name = "device_name")
    var deviceName: String? = null,

    @Column(name = "host", length = 15, nullable = false)
    var host: String? = null,

    @Column(name = "channel", length = 10, nullable = false)
    var channel: String? = null,

    @Column(name = "app_version")
    var appVersion: String? = null,

    @Column(name = "os_version")
    var osVersion: String? = null,

    @Column(name = "language")
    var language: String? = null,

    @Column(name = "bind_time")
    var bindTime: Long? = null,

    @Column(name = "longitude")
    var longitude: Double? = null,

    @Column(name = "latitude")
    var latitude: Double? = null,

    @Column(name = "location")
    var location: String? = null,

    @Column(name = "state")
    var state: Int = 0
) {
    companion object {
        const val STATE_ACTIVE = 0
        const val STATE_APNS = 1
        const val STATE_INACTIVE = 2

        const val CHANNEL_IOS = "ios"
        const val CHANNEL_ANDROID = "android"
        const val CHANNEL_WINDOWS = "windows"
        const val CHANNEL_MAC = "mac"
        const val CHANNEL_WEB = "web"
    }
}
