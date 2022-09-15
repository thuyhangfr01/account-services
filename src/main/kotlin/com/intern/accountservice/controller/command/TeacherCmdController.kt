package com.intern.accountservice.controller.command

import com.intern.accountservice.cqrs.command.domain.StudentCmd
import com.intern.accountservice.cqrs.command.domain.TeacherCmd
import com.intern.accountservice.cqrs.command.exceptions.StudentCmdException
import com.intern.accountservice.cqrs.command.exceptions.TeacherCmdException
import com.intern.accountservice.cqrs.command.repositories.StudentCmdRepository
import com.intern.accountservice.cqrs.command.repositories.TeacherCmdRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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

    //update status
    @PutMapping("/teacher/status/{id}")
    fun updateStatusById(
        @PathVariable(value = "id") teacherId: Long,
        @Valid @RequestBody newStatus: TeacherCmd): ResponseEntity<TeacherCmd> {

        if(teacherCmdRepository.findByIdOrNull(teacherId) == null) throw TeacherCmdException("Page not found!")
        else return teacherCmdRepository.findById(teacherId).map { existingTeacher ->
            val updatedTeacher: TeacherCmd = existingTeacher
                .copy(status = newStatus.status)
            ResponseEntity.ok().body(teacherCmdRepository.save(updatedTeacher))
        }.orElse(ResponseEntity.notFound().build())
    }
}
