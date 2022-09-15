package com.intern.accountservice.cqrs.command.repositories

import com.intern.accountservice.cqrs.command.domain.TeacherCmd
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherCmdRepository: JpaRepository<TeacherCmd, Long> {
}