package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.entity.Degree
import com.intern.accountservice.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class UserQueryController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/CertificatesOfTeacher/idTeacher={idTeacher}")
    fun getCertificatesOfTeacher(@PathVariable(value = "idTeacher") idTeacher: Long): ResponseEntity<Degree> {
        return userService.getCertificatesByTeacherId(idTeacher)
    }
}