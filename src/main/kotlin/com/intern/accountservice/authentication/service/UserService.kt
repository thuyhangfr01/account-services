package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.exception.AccountException
import com.intern.accountservice.authentication.model.Role
import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.repository.RoleRepository
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.sql.SQLIntegrityConstraintViolationException


@Service
class UserService() {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun registerNewAdmin(user: User): User? {
        val role: Role = roleRepository?.findById(1)!!.get()
        val adminRoles: MutableSet<Role?> = HashSet()
        adminRoles.add(role)

        user.name
        user.email
        user.userName
        user.password = getEncodedPassword(user.password)
        user.role = adminRoles
        if(user.name.isNullOrBlank() || user.email.isNullOrBlank() ||
            user.userName.isNullOrBlank() || user.password.isNullOrBlank()){
            throw AccountException(" Please fill in the information below items(*) not be empty. ")
        }
        return userRepository!!.save(user)
    }

    @Throws(SQLIntegrityConstraintViolationException :: class, AccountException :: class)
    fun registerNewStudent(user: User): User? {
        val role: Role = roleRepository?.findById(2)!!.get()
        val studentRoles: MutableSet<Role?> = HashSet()
        studentRoles.add(role)

        user.name
        user.email
        user.userName
        user.password = getEncodedPassword(user.password)
        user.role = studentRoles
        if(user.name.isNullOrBlank() || user.email.isNullOrBlank() ||
            user.userName.isNullOrBlank() || user.password.isNullOrBlank()){
            throw AccountException(" Please fill in the information below items(*) not be empty. ")
        }
        return userRepository!!.save(user)
    }

    fun registerNewTeacher(user: User): User? {
        val role: Role = roleRepository?.findById(3)!!.get()
        val teacherRoles: MutableSet<Role?> = HashSet()
        teacherRoles.add(role)

        user.name
        user.email
        user.userName
        user.password = getEncodedPassword(user.password)
        user.role = teacherRoles
        if(user.name.isNullOrBlank() || user.email.isNullOrBlank() ||
            user.userName.isNullOrBlank() || user.password.isNullOrBlank()){
            throw AccountException(" Please fill in the information below items(*) not be empty. ")
        }
        return userRepository!!.save(user)
    }

    fun getEncodedPassword(password: String?): String? {
        return passwordEncoder!!.encode(password)
    }
}