package com.intern.accountservice.controller.query

import com.intern.accountservice.cqrs.query.domain.Student
import com.intern.accountservice.cqrs.query.repositories.StudentRepository
import com.intern.accountservice.cqrs.query.services.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class StudentQueryController (val studentService: StudentService) {
    @GetMapping("/students")
    fun getAllLessons() = ResponseEntity(studentService.findAll(), HttpStatus.OK)
}