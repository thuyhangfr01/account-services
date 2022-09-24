package com.intern.accountservice.authentication.repository

import com.intern.accountservice.authentication.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long>