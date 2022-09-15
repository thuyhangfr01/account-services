package com.intern.accountservice.cqrs.command.domain

import javax.persistence.*

@Entity
@Table(name = "teachers")
data class TeacherCmd(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 1,

    @Column(name = "namee")
    var name: String? = "",

    @Column(name = "age")
    var age: Int? = 0,

    @Column(name = "gender")
    var gender: String? = "",

    @Column(name = "address")
    var address: String? = "",

    @Column(name = "email")
    var email: String? = "",

    @Column(name = "phone")
    var phone: String? = "",

    @Column(name = "avatar")
    var avatar: String? = "",

    @Column(name = "username")
    var username: String? = "",

    @Column(name = "pass")
    var pass: String? = "",

    @Column(name = "statuss")
    var status: String? = ""
)