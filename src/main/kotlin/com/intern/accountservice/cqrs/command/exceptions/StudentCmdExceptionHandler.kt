package com.intern.accountservice.cqrs.command.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class StudentCmdException(override val message: String) : RuntimeException(message)

@ControllerAdvice
class StudentCmdExceptionHandler {
    @ExceptionHandler(StudentCmdException::class)
    fun handleMonitoringException(ex: StudentCmdException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }

}