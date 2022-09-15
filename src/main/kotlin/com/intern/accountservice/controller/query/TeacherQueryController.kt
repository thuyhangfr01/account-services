package com.intern.accountservice.controller.query

import com.intern.accountservice.cqrs.query.services.TeacherService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TeacherQueryController (val teacherService: TeacherService) {
    @GetMapping("/teachers")
    fun getAllLessons() = ResponseEntity(teacherService.findAll(), HttpStatus.OK)
}