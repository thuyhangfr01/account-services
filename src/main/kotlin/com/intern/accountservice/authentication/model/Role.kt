package com.intern.accountservice.authentication.model

import javax.persistence.*

@Entity
@Table(name = "role")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "namee")
    var name: String? = null

    constructor() {}
    constructor(name: String?) : super() {
        this.name = name
    }
}