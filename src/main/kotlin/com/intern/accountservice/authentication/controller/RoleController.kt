package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.model.Role
import com.intern.accountservice.authentication.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private val roleService: RoleService? = null

    @PostMapping("/createNewRole")
    fun createNewRole(@RequestBody role: Role): Role? {
        return roleService!!.createNewRole(role)
    }
}