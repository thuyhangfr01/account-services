package com.intern.accountservice.cqrs.query.repositories

import com.intern.accountservice.cqrs.query.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : JpaRepository<Teacher, Long> {

}