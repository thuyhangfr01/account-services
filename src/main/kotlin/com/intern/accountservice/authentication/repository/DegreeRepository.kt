package com.intern.accountservice.authentication.repository

import com.intern.accountservice.authentication.entity.Degree
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface DegreeRepository: JpaRepository<Degree, Long>

