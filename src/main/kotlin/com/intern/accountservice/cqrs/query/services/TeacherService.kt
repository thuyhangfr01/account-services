package com.intern.accountservice.cqrs.query.services

import com.intern.accountservice.cqrs.query.domain.Student
import com.intern.accountservice.cqrs.query.domain.Teacher
import com.intern.accountservice.cqrs.query.repositories.StudentRepository
import com.intern.accountservice.cqrs.query.repositories.TeacherRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TeacherService (val teacherRepository: TeacherRepository) {
    fun findAll(): MutableList<Teacher> {
        return teacherRepository.findAll()
    }

    fun getInfoTeacher(teacherId: Long): ResponseEntity<Teacher> {
        return teacherRepository.findById(teacherId).map { teacher ->
            ResponseEntity.ok(teacher)
        }.orElse(ResponseEntity.notFound().build())
    }
}