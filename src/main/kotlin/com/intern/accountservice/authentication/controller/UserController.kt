package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private val userService: UserService? = null

    @PostConstruct
    fun initRoleAndUser() {
        return userService!!.initRoleAndUser()
    }

    @PostMapping("/registerNewUser")
    fun registerNewUser(@RequestBody user: User?): User? {
        return userService!!.registerNewUser(user!!)
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    fun forAdmin(): String? {
        return "This URL is only accessible to the admin"
    }

    @GetMapping("/forStudent")
    @PreAuthorize("hasRole('Student')")
    fun forStudent(): String? {
        return "This URL is only accessible to the student"
    }

    @GetMapping("/forTeacher")
    @PreAuthorize("hasRole('Teacher')")
    fun forTeacher(): String? {
        return "This URL is only accessible to the teacher"
    }
}