package com.intern.accountservice.authentication.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "user", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name = "namee")
    var name: String? = null

    @Column(name = "age")
    var age: Int? = 0

    @Column(name = "gender")
    var gender: String? = ""

    @Column(name = "address")
    var address: String? = ""

    @Column(name = "phone")
    var phone: String? = ""

    @Column(name = "avatar")
    var avatar: String? = ""

    @Column(name = "email", unique = true)
    var email: String? = ""

    @Column(name = "pass")
    var password: String? = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }


    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Collection<Role>? = null

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}