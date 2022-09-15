package com.intern.accountservice.controller.command

import com.intern.accountservice.cqrs.command.domain.StudentCmd
import com.intern.accountservice.cqrs.command.exceptions.StudentCmdException
import com.intern.accountservice.cqrs.command.repositories.StudentCmdRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StudentCmdController (val studentCmdRepository: StudentCmdRepository) {
    @PostMapping("/student")
    fun createNewStudent(@RequestBody studentCmd: StudentCmd): StudentCmd {
        if (studentCmd.name.isNullOrEmpty() || studentCmd.age == 0 || studentCmd.gender.isNullOrEmpty() ||
            studentCmd.address.isNullOrEmpty() || studentCmd.email.isNullOrEmpty() || studentCmd.phone.isNullOrEmpty() ||
            studentCmd.username.isNullOrEmpty() || studentCmd.pass.isNullOrEmpty())
                throw StudentCmdException("Please complete all information!")
        else return studentCmdRepository.save(studentCmd)
    }
}


