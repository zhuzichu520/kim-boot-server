package com.chuzi.kim.entity

import jakarta.persistence.*

@Entity
@Table(name = "t_im_message")
data class Message(
    @Id @Column(name = "id")
    var id: String? = null,

    @Column(name = "scene")
    var scene: Int = 0,

    @Column(name = "type")
    var type: Int = 0,

    @Column(name = "subType")
    var subType: Int = 0,

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "content", length = 5000)
    var content: String? = null,

    @Column(name = "sender", nullable = false)
    var sender: String? = null,

    @Column(name = "receiver", nullable = false)
    var receiver: String? = null,

    @Column(name = "extra")
    var extra: String? = null,

    @Column(name = "timestamp", nullable = false)
    var timestamp: Long = System.currentTimeMillis()
)