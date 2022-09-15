package com.intern.accountservice.cqrs.query.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "students")
data class Student(
    @Id
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
    var pass: String? = ""
)
