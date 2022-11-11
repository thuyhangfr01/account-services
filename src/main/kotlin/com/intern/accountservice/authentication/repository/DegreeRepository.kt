package com.intern.accountservice.authentication.repository

import com.intern.accountservice.authentication.entity.Degree
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DegreeRepository : JpaRepository<Degree, Long> {
    fun findByUserId(userId: Long?): List<Degree>
    fun findByUserIdAndStatus(userId: Long?, status: Int?): List<Degree>
}