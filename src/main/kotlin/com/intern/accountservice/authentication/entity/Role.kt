package com.intern.accountservice.authentication.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name = "namee")
    var name: String? = ""
}