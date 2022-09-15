package com.intern.accountservice.cqrs.query.services

import com.intern.accountservice.cqrs.query.domain.Student
import com.intern.accountservice.cqrs.query.repositories.StudentRepository
import org.springframework.stereotype.Component

@Component
class StudentService (val studentRepository: StudentRepository) {
    fun findAll(): MutableList<Student> {
        return studentRepository.findAll()
    }
}