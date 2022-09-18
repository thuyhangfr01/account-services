package com.intern.accountservice.authentication.dto

class UserRegistrationDto {
    var name: String? = null
    var age: Int? = 0
    var gender: String? = null
    var address: String? = null
    var phone: String? = null
    var avatar: String? = null
    var email: String? = null
    var password: String? = null

    constructor() {}
    constructor(
        name: String?,
        age: Int?,
        gender: String?,
        address: String?,
        phone: String?,
        avatar: String?,
        email: String?,
        password: String?
    ) {
        this.name = name
        this.age = age
        this.gender = gender
        this.address = address
        this.phone = phone
        this.avatar = avatar
        this.email = email
        this.password = password
    }

}