package com.intern.accountservice.authentication.service

import com.intern.accountservice.authentication.entity.Degree
import com.intern.accountservice.authentication.exception.AccountException
import com.intern.accountservice.authentication.entity.Role
import com.intern.accountservice.authentication.entity.User
import com.intern.accountservice.authentication.repository.DegreeRepository
import com.intern.accountservice.authentication.repository.RoleRepository
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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
    private val degreeRepository: DegreeRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    fun registerNewAdmin(user: User): User? {
        val role: Role = roleRepository?.findById(1)!!.get()
        val adminRoles: MutableSet<Role?> = HashSet()
        adminRoles.add(role)

        user.name
        user.age
        user.gender
        user.address
        user.phone
        user.avatar
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
        user.age
        user.gender
        user.address
        user.phone
        user.avatar
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
        user.age
        user.gender
        user.address
        user.phone
        user.avatar
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

    fun updateUserInfo(idUser: Long, user: User?): ResponseEntity<User> {
        return userRepository?.findById(idUser)?.map { existingUser ->
            val updateUser: User = existingUser
                .copy(name = user?.name,
                    age = user?.age,
                    gender = user?.gender,
                    address = user?.address,
                    phone = user?.phone,
                    avatar = user?.avatar,
                    email = user?.email,
                    userName = user?.userName,
                    password = getEncodedPassword(user?.password))
            ResponseEntity.ok().body(userRepository?.save(updateUser))
        }!!.orElse(ResponseEntity.notFound().build())
    }


    fun getEncodedPassword(password: String?): String? {
        return passwordEncoder!!.encode(password)
    }

    fun addDegree(degree : Degree): ResponseEntity<Degree>?{
        return ResponseEntity.ok(degreeRepository?.save(degree))
    }

    fun updateDegree(idTeacher : Long, degree : Degree?): ResponseEntity<Degree>?{
        return degreeRepository?.findById(idTeacher)?.map { existingDegree ->
            val updateDegree: Degree = existingDegree
                .copy(img1 = degree?.img1,
                    img2 = degree?.img2,
                    img3 = degree?.img3,
                    img4 = degree?.img4,
                    img5 = degree?.img5
                )
            ResponseEntity.ok().body(degreeRepository?.save(updateDegree))
        }!!.orElse(ResponseEntity.notFound().build())
    }
}