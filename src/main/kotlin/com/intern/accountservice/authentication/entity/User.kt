package com.intern.accountservice.authentication.entity

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name = "namee")
    var name: String? = null
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "age")
    var age: Int? = 0
        set(value) {
            field = value
        }

    @Column(name = "gender")
    var gender: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "address")
    var address: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "phone")
    var phone: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Column(name = "avatar")
    var avatar: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @Email
    @NotBlank(message = "Email is mandatory!")
    @Column(name = "email")
    var email: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @NotBlank(message = "Username is mandatory!")
    @Column(name = "username", unique = true)
    var userName: String? = ""
        set(value) {
            if(value!!.isNotEmpty())  field = value
        }

    @NotBlank(message = "Password is mandatory!")
    @Column(name = "pass")
    var password: String? = ""
        //@JsonIgnore
//        get() = field
//        set(value) {
//            val passwordEncoder = BCryptPasswordEncoder()
//            field = passwordEncoder.encode(value)
//        }


    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var role: MutableSet<Role?>? = null

    constructor(role: MutableSet<Role?>?) {
        this.role = role
    }

    constructor(age: Int?) {
        this.age = age
    }

    constructor()

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}