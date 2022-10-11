package com.intern.accountservice.authentication.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.function.Consumer

class AccountException(override val message: String) : RuntimeException(message)

@ControllerAdvice
class AccountExceptionHandler {
    @ExceptionHandler(AccountException::class)
    fun handleMonitoringException(ex: AccountException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }
}
