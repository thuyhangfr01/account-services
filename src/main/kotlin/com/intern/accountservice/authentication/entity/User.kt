package com.intern.accountservice.authentication.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(name = "namee")
    var name: String? = "",

    @Column(name = "age")
    var age: Int? = 0,

    @Column(name = "gender")
    var gender: String? = "M",

    @Column(name = "address")
    var address: String? = "",

    @Column(name = "phone")
    var phone: String? = "",

    @Column(name = "avatar")
    var avatar: String? = "",

    @Email
    @NotBlank(message = "Email is mandatory!")
    @Column(name = "email")
    var email: String? = "",

    @NotBlank(message = "Username is mandatory!")
    @Column(name = "username", unique = true)
    var userName: String? = "",

    //@JsonIgnore
    @NotBlank(message = "Password is mandatory!")
    @Column(name = "pass")
    var password: String? = "",

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var role: MutableSet<Role?>? = null
)