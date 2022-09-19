package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
//    fun save(registrationDto: UserRegistrationDto?): User?
//    val all: List<User?>?
    fun save(user: User): User{
        return this.userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return this.userRepository.findByUsername(username)
    }

    fun getById(id: Int): User {
        return this.userRepository.getById(id.toLong())
    }
}