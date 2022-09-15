package com.intern.accountservice.cqrs.command.repositories

import com.intern.accountservice.cqrs.command.domain.StudentCmd
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentCmdRepository: JpaRepository<StudentCmd, Long> {
}