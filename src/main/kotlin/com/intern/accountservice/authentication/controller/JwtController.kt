package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.model.JwtRequest
import com.intern.accountservice.authentication.model.JwtResponse
import com.intern.accountservice.authentication.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/api")
class JwtController {
    @Autowired
    private val jwtService: JwtService? = null

    @PostMapping("/authenticate")
    @Throws(Exception::class)
    fun createJwtToken(@RequestBody jwtRequest: JwtRequest?): JwtResponse? {
        return jwtService!!.createJwtToken(jwtRequest!!)
    }
}