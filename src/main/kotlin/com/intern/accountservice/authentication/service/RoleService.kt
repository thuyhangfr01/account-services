package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.model.Role
import com.intern.accountservice.authentication.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
public class RoleService {
    @Autowired
    private var roleRepository: RoleRepository? = null

    fun createNewRole(role: Role): Role? {
        return roleRepository?.save(role)
    }
}



