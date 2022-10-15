package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.entity.Degree
import com.intern.accountservice.authentication.entity.User
import com.intern.accountservice.authentication.repository.UserRepository
import com.intern.accountservice.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

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

    @PutMapping("/updateUserInfo/idUser={idUser}")
    fun updateUserInfo(
        @PathVariable(value = "idUser") idUser: Long,
        @Valid @RequestBody user: User?,
    ) : ResponseEntity<User> {
        return userService.updateUserInfo(idUser, user)
    }

    @PostMapping("/addDegree")
    fun addDegree(
        @RequestBody degree: Degree
    ): ResponseEntity<Degree>? {
        return userService.addDegree(degree)
    }

    @PutMapping("/updateDegree/idTeacher={idTeacher}")
    fun updateUDegree(
        @PathVariable(value = "idTeacher") idTeacher: Long,
        @Valid @RequestBody degree: Degree,
    ) : ResponseEntity<Degree>? {
        return userService.updateDegree(idTeacher, degree)
    }
}