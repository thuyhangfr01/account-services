package com.intern.accountservice.authentication.controller

import com.intern.accountservice.authentication.entity.Degree
import com.intern.accountservice.authentication.entity.JwtResponse
import com.intern.accountservice.authentication.entity.JwtResponseStatus
import com.intern.accountservice.authentication.exception.AccountException
import com.intern.accountservice.authentication.repository.DegreeRepository
import com.intern.accountservice.authentication.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
public class DegreeController {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var degreeRepository: DegreeRepository
    @GetMapping("/user/{userId}/degrees")
    fun getAllDegreesByUserId(
        @PathVariable(value = "userId") userId: Long
    ): ResponseEntity<List<Degree>>? {
        val degrees: List<Degree> = degreeRepository.findByUserId(userId)
        return ResponseEntity<List<Degree>>(degrees, HttpStatus.OK)
    }

    @GetMapping("/user/{userId}/degrees/status/{status}")
    fun getAllDegreesByUserIdAndStatus(
        @PathVariable(value = "userId") userId: Long,
        @PathVariable(value = "status") status: Int
    ): ResponseEntity<List<Degree>>? {
        val degrees: List<Degree> = degreeRepository.findByUserIdAndStatus(userId, status)
        return ResponseEntity<List<Degree>>(degrees, HttpStatus.OK)
    }

    @GetMapping("/degree/{id}")
    fun getDegreeById(
        @PathVariable(value = "id") id: Long
    ): ResponseEntity<Degree?>? {
        val degree: Degree = degreeRepository.findById(id)
            .orElseThrow{ AccountException("Not found user")}
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
            throw AccountException("Not found degree with id = $id");
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
            throw AccountException("Not found degree with id = $id");
        }
        return degreeRepository.findById(id).map { existingDegree ->
            val updateDegree: Degree = existingDegree
                .copy(status = degree.status)
            ResponseEntity.ok().body(degreeRepository.save(updateDegree))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/degree/{id}")
    fun deleteDegree(@PathVariable("id") id: Long): JwtResponseStatus {
        if (!degreeRepository.existsById(id)) {
            throw AccountException("Not found degree with id = $id");
        }
        degreeRepository.deleteById(id)
        val status = HttpStatus.OK
        return JwtResponseStatus(status)
    }
}