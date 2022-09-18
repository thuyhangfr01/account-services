package com.intern.accountservice

//import com.intern.accountservice.authentication.config.SecurityConfiguration
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@OpenAPIDefinition
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AccountServiceApplication

fun main(args: Array<String>) {
	runApplication<AccountServiceApplication>(*args)
}
