package com.intern.accountservice.authentication.entity

import org.springframework.http.HttpStatus

class JwtResponse(var status: HttpStatus, var user: User?, var jwtToken: String?)