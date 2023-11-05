package com.chuzi.kim.entity

import jakarta.persistence.*


@Entity
@Table(name = "t_im_friend")
data class Friend(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: String? = null,

    @Column(name = "uid", nullable = false)
    var uid: String? = null,

    @Column(name = "friend_id", nullable = false)
    var friendId: String? = null,

    )