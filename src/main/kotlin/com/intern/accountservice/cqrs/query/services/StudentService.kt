package com.intern.accountservice.cqrs.query.services

import com.intern.accountservice.cqrs.query.domain.Student
import com.intern.accountservice.cqrs.query.repositories.StudentRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class StudentService (val studentRepository: StudentRepository) {
    //get all accounts
    fun findAll(): MutableList<Student> {
        return studentRepository.findAll()
    }

    fun getInfoStudent(studentId: Long): ResponseEntity<Student> {
        return studentRepository.findById(studentId).map { student ->
            ResponseEntity.ok(student)
        }.orElse(ResponseEntity.notFound().build())
    }
}