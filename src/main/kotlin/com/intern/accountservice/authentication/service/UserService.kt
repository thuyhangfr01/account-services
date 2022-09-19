package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.model.Role
import com.intern.accountservice.authentication.model.User
import com.intern.accountservice.authentication.repository.RoleRepository
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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

        val adminUser = User()
        adminUser.name = "Cao Thị Thúy Hằng"
        adminUser.age = 21
        adminUser.gender = "F"
        adminUser.address = "02 Thanh Sơn, Đà Nẵng"
        adminUser.phone = "0765700999"
        adminUser.avatar = "ava1.png"
        adminUser.email = "thuyhang@gmail.com"
        adminUser.username = "hangadmin"
        adminUser.password = "admin123"
        val adminRoles: MutableSet<Role?> = HashSet()
        adminRoles.add(adminRole)
        adminUser.role = adminRoles
        userRepository!!.save(adminUser)

//        val studentUser = User()
//        studentUser.name = "Nguyễn Thị A "
//        studentUser.age = 21
//        studentUser.gender = "F"
//        studentUser.address = "23 Quang Trung, Đà Nẵng"
//        studentUser.phone = "0987789987"
//        studentUser.avatar = "ava2.png"
//        studentUser.email = "a@gmail.com"
//        studentUser.username = "astudent"
//        studentUser.password = "abc123456"
//        val studentRoles: MutableSet<Role?> = HashSet()
//        studentRoles.add(studentRole)
//        studentUser.role = studentRoles
//        userRepository!!.save(studentUser)
    }

    fun registerNewUser(user: User): User? {
        val role: Role = roleRepository?.findById("User")!!.get()
        val studentRoles: MutableSet<Role?> = HashSet()
        studentRoles.add(role)
        user.name
        user.email
        user.username
        user.password
        user.role = studentRoles
        return userRepository!!.save(user);
    }

    fun getEncodedPassword(password: String?): String? {
        return passwordEncoder!!.encode(password)
    }
}