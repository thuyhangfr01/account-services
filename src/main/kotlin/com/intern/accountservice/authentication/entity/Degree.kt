package com.intern.accountservice.authentication.entity

import javax.persistence.*

@Entity
@Table(name = "degrees_teacher")
data class Degree (
    @Id
    @Column(name = "user_id")
    var userId: Long? = 0,

    @Column(name = "img1")
    var img1: String? = "",

    @Column(name = "img2")
    var img2: String? = "",

    @Column(name = "img3")
    var img3: String? = "",

    @Column(name = "img4")
    var img4: String? = "",

    @Column(name = "img5")
    var img5: String? = ""
)