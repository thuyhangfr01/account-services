package com.intern.accountservice.authentication.service

import com.fasterxml.jackson.annotation.JsonIgnore
import com.intern.accountservice.authentication.model.Role
import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.repository.RoleRepository
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService() {
    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun initRoleAndUser() {
        val adminRole = Role()
        adminRole.name = "ROLE_ADMIN"
        roleRepository?.save(adminRole)

        val studentRole = Role()
        studentRole.name = "ROLE_STUDENT"
        roleRepository?.save(studentRole)

        val teacherRole = Role()
        teacherRole.name = "ROLE_TEACHER"
        roleRepository?.save(teacherRole)
    }

    fun registerNewAdmin(user: User): User? {
        val role: Role = roleRepository?.findById(1)!!.get()
        val adminRoles: MutableSet<Role?> = HashSet()
        adminRoles.add(role)
        user.name
        user.email
        user.userName
        user.password = getEncodedPassword(user.password)
        user.role = adminRoles
        return userRepository!!.save(user);
    }

    fun registerNewStudent(user: User): User? {
        val role: Role = roleRepository?.findById(2)!!.get()
        val studentRoles: MutableSet<Role?> = HashSet()
        studentRoles.add(role)
        user.name
        user.email
        user.userName
        user.password = getEncodedPassword(user.password)
        user.role = studentRoles
        return userRepository!!.save(user);
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
        return userRepository!!.save(user);
    }

    fun getEncodedPassword(password: String?): String? {
        return passwordEncoder!!.encode(password)
    }
}