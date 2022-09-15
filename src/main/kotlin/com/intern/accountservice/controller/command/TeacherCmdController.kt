package com.intern.accountservice.controller.command

import com.intern.accountservice.cqrs.command.domain.StudentCmd
import com.intern.accountservice.cqrs.command.domain.TeacherCmd
import com.intern.accountservice.cqrs.command.exceptions.StudentCmdException
import com.intern.accountservice.cqrs.command.exceptions.TeacherCmdException
import com.intern.accountservice.cqrs.command.repositories.StudentCmdRepository
import com.intern.accountservice.cqrs.command.repositories.TeacherCmdRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TeacherCmdController (val teacherCmdRepository: TeacherCmdRepository) {
    @PostMapping("/teacher")
    fun createNewTeacher(@RequestBody teacherCmd: TeacherCmd): TeacherCmd {
        if (teacherCmd.name.isNullOrEmpty() || teacherCmd.age == 0 || teacherCmd.gender.isNullOrEmpty() ||
            teacherCmd.address.isNullOrEmpty() || teacherCmd.email.isNullOrEmpty() || teacherCmd.phone.isNullOrEmpty() ||
            teacherCmd.username.isNullOrEmpty() || teacherCmd.pass.isNullOrEmpty())
            throw TeacherCmdException("Please complete all information!")
        else return teacherCmdRepository.save(teacherCmd)
    }
}
