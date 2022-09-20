package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("/registerNewAdmin")
    fun registerNewAdmin(@RequestBody user: User?): User? {
        return userService!!.registerNewAdmin(user!!)
    }

    @PostMapping("/registerNewStudent")
    fun registerNewStudent(@RequestBody user: User?): User? {
        return userService!!.registerNewStudent(user!!)
    }

    @PostMapping("/registerNewTeacher")
    fun registerNewTeacher(@RequestBody user: User?): User? {
        return userService!!.registerNewTeacher(user!!)
    }

    @GetMapping("/forAdmin")
    //@PreAuthorize("hasRole()")
    fun forAdmin(): String? {
        return "This URL is only accessible to the admin"
    }

    @GetMapping("/forStudent")
    //@PreAuthorize("hasRole(2)")
    fun forStudent(): String? {
        return "This URL is only accessible to the student"
    }

    @GetMapping("/forTeacher")
    //@PreAuthorize("hasRole(3)")
    fun forTeacher(): String? {
        return "This URL is only accessible to the teacher"
    }
}