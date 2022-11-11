package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.entity.Degree
import com.intern.accountservice.authentication.entity.User
import com.intern.accountservice.authentication.exception.AccountException
import com.intern.accountservice.authentication.repository.DegreeRepository
import com.intern.accountservice.authentication.repository.UserRepository
import com.intern.accountservice.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var degreeRepository: DegreeRepository



    @PostMapping("/registerNewAdmin")
    fun registerNewAdmin(@RequestBody user: User?): User? {
        return userService!!.registerNewAdmin(user!!)
    }

    @PostMapping("/registerNewStudent")
    fun registerNewStudent(@RequestBody user: User?): User? {
        return userService!!.registerNewStudent(user!!)
    }

    @PostMapping("/registerNewTeacher")
    fun registerNewTeacher(@RequestBody user: User?): User? {
        return userService!!.registerNewTeacher(user!!)
    }

    @GetMapping("/forAdmin")
    //@PreAuthorize("hasRole()")
    fun forAdmin(): String? {
        return "This URL is only accessible to the admin"
    }

    @GetMapping("/forStudent")
    //@PreAuthorize("hasRole(2)")
    fun forStudent(): String? {
        return "This URL is only accessible to the student"
    }

    @GetMapping("/forTeacher")
    //@PreAuthorize("hasRole(3)")
    fun forTeacher(): String? {
        return "This URL is only accessible to the teacher"
    }

    @PutMapping("/updateUserInfo/idUser={idUser}")
    fun updateUserInfo(
        @PathVariable(value = "idUser") idUser: Long,
        @Valid @RequestBody user: User?,
    ): ResponseEntity<User> {
        return userService.updateUserInfo(idUser, user)
    }

    //Degrees of teacher
    @GetMapping("/user/{userId}/degrees")
    fun getAllDegreesByTutorialId(
        @PathVariable(value = "userId") userId: Long
    ): ResponseEntity<List<Degree>>? {
        val degrees: List<Degree> = degreeRepository.findByUserId(userId)
        return ResponseEntity<List<Degree>>(degrees, HttpStatus.OK)
    }

    @GetMapping("/degree/{id}")
    fun getDegreeById(@PathVariable(value = "id") id: Long): ResponseEntity<Degree?>? {
        val degree: Degree = degreeRepository.findById(id)
            .orElseThrow { AccountException("Not found Comment with id = $id") }
        return ResponseEntity<Degree?>(degree, HttpStatus.OK)
    }

    @PostMapping("/user/{userId}/degrees")
    fun createDegrees(
        @PathVariable(value = "userId") userId: Long,
        @Valid @RequestBody degreeRequest: Degree
    ): Any? {
        val degree: Degree = userRepository.findById(userId).map { user ->
            degreeRequest.user = user
            degreeRepository.save(degreeRequest)
        }.orElseThrow { AccountException("Not found user") }
        return ResponseEntity<Degree?>(degree, HttpStatus.CREATED)
    }

    @PutMapping("/degree/{id}")
    fun updateDegree(
        @PathVariable("id") id: Long,
        @Valid @RequestBody degree: Degree,
    ) : ResponseEntity<Degree>? {
        if (!degreeRepository.existsById(id)) {
            throw AccountException("Not found degree with id = " + id);
        }
        return degreeRepository.findById(id).map { existingDegree ->
            val updateDegree: Degree = existingDegree
                .copy(name = degree.name,
                    placeOfIssue = degree.placeOfIssue,
                    dateOfIssue = degree.dateOfIssue,
                    dateOfExpiry = degree.dateOfExpiry,
                    img1 = degree.img1,
                    img2 = degree.img2,
                    img3 = degree.img3,
                    img4 = degree.img4,
                    img5 = degree.img5)
            ResponseEntity.ok().body(degreeRepository.save(updateDegree))
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/degree/status/{id}")
    fun updateStatusOfDegree(
        @PathVariable("id") id: Long,
        @Valid @RequestBody degree: Degree,
    ) : ResponseEntity<Degree>? {
        if (!degreeRepository.existsById(id)) {
            throw AccountException("Not found degree with id = " + id);
        }
        return degreeRepository.findById(id).map { existingDegree ->
            val updateDegree: Degree = existingDegree
                .copy(status = degree.status)
            ResponseEntity.ok().body(degreeRepository.save(updateDegree))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/degree/{id}")
    fun deleteDegree(@PathVariable("id") id: Long): ResponseEntity<HttpStatus?>? {
        if (!degreeRepository.existsById(id)) {
            throw AccountException("Not found degree with id = " + id);
        }
        degreeRepository.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}