package com.intern.accountservice.authentication.entity

import javax.persistence.*

@Entity
@Table(name = "degrees_teacher")
class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "user_id")
    var userId: Int? = 0
        set(value) {
            field = value
        }

    @Column(name = "role_id")
    var roleId: Int? = 0
        set(value) {
            field = value
        }

    @Column(name = "img1")
    var img1: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "img2")
    var img2: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "img3")
    var img3: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "img4")
    var img4: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "img5")
    var img5: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }
}