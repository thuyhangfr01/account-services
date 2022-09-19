package com.intern.accountservice.authentication.repository

import com.intern.accountservice.authentication.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
public interface RoleRepository : CrudRepository<Role, String> {

}