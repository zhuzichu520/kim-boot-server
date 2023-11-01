package com.chuzi.kim.entity

import jakarta.persistence.*

@Entity
@Table(name = "t_im_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "uid", length = 32, nullable = false)
    var uid: String? = null,

    @Column(name = "password", length = 32, nullable = false)
    var password: String? = null,

    @Column(name = "name", length = 10)
    var name: String? = null,

    @Column(name = "avatar")
    var avatar: String? = null,

    @Column(name = "signature")
    var signature: String? = null,

    @Column(name = "email", length = 64)
    var email: String? = null,

    @Column(name = "mobile", length = 15)
    var mobile: String? = null,

    @Column(name = "birthday")
    var birthday: String? = null,

    @Column(name = "gender")
    var gender: Int? = null,

    @Column(name = "extension", length = 1024)
    var extension: String? = null,

    ) {
    companion object {
        const val STATE_MALE = 0
        const val STATE_FEMALE = 1
    }

}