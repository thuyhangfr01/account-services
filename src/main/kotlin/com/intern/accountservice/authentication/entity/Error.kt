package com.intern.accountservice.authentication.entity

import org.springframework.http.HttpStatus

class Error (var status: HttpStatus, message: String)