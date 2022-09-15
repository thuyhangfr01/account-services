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
    fun createNewStudent(@RequestBody studentCmd: StudentCmd): StudentCmd =
        //if (studentCmd.name.isNullOrEmpty()) throw StudentCmdException("name not found")
         studentCmdRepository.save(studentCmd)

}

//    @PostMapping("/student")
//    fun createNewStudent(@Valid @RequestBody studentCmd: StudentCmd): StudentCmd =
//        studentCmdRepository.save(studentCmd)
//
//    fun getToken(appID: String?, appCertificate: String?, channelName: String?): MutableMap<String, String> {
//        if(appID.isNullOrEmpty()) throw AgoraAppException("appID not found")
//        if(appCertificate.isNullOrEmpty()) throw AgoraAppException("appCertificate not found")
//        if(channelName.isNullOrEmpty()) throw AgoraAppException("channelName not found")
//
//        var tokens: MutableMap<String, String> = mutableMapOf()
//        var tokenBuilder = RtcTokenBuilder()
//        var timestamp: Int = ((System.currentTimeMillis() / 1000 + 3600).toInt())
//        var token: String = tokenBuilder.buildTokenWithUid(appID, appCertificate, channelName, 0, Role.Role_Publisher, timestamp)
//        tokens["token"] = token
//        return tokens
//    }

//            @PutMapping("/student/{id}")
//    fun updateAccountStudentById(
//        @PathVariable(value = "id") studentId: Long,
//        @Valid @RequestBody newStudent: StudentCmd): ResponseEntity<StudentCmd> {
//
//        return studentCmdRepository.findById(studentId).map { existingStudent->
//            val updatedStudent: StudentCmd = existingStudent
//                .copy(name = newStudent.name,
//                    age = newStudent.age,
//                    gender = newStudent.gender,
//                    address = newStudent.address,
//                    email = newStudent.email,
//                    phone = newStudent.phone,
//                    avatar = newStudent.avatar,
//                    username = newStudent.username,
//                    pass = newStudent.pass)
//            ResponseEntity.ok().body(studentCmdRepository.save(updatedStudent))
//        }.orElse(ResponseEntity.notFound().build())
//    }
//}

