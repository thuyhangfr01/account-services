package com.intern.accountservice.authentication.repository

import com.intern.accountservice.authentication.entity.User
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    @Query("Select * from user where username like '%:username%'")
    fun findByUserName(@Param("username") username: String?): User?
}