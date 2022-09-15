package com.intern.accountservice.cqrs.command.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class TeacherCmdException(override val message: String) : RuntimeException(message)

@ControllerAdvice
class TeacherCmdExceptionHandler {
    @ExceptionHandler(TeacherCmdException::class)
    fun handleMonitoringException(ex: TeacherCmdException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }

}