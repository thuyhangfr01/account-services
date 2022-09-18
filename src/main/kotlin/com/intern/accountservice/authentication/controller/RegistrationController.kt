package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.dto.LoginDto
import com.intern.accountservice.authentication.dto.Message
import com.intern.accountservice.authentication.dto.UserRegistrationDto
import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class RegistrationController(private val userService: UserService) {
//    @ModelAttribute("user")
//    fun userRegistrationDto(): UserRegistrationDto {
//        return UserRegistrationDto()
//    }
//
//    @GetMapping
//    fun showRegistrationForm(): String {
//        return "registration"
//    }
//
//    @PostMapping
//    fun registerUserAccount(@ModelAttribute("user") registrationDto: UserRegistrationDto?): String {
//        userService.save(registrationDto)
//        return "redirect:/registration?success"
//    }

    @PostMapping("/register")
    fun register(@RequestBody body: UserRegistrationDto) : ResponseEntity<User>{
        val user = User()
        user.name = body.name
        user.age = body.age
        user.gender = body.gender
        user.address = body.address
        user.phone = body.phone
        user.avatar = body.avatar
        user.email = body.email
        user.password = body.password
        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("User not found!"))

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("invalid password!"))
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @GetMapping("/user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.userService.getById(body.issuer.toInt()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

}