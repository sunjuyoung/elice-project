package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.academy_management.dto.response.AcademyCountDTO;
import com.eliceteam8.edupay.academy_management.service.AcademyService;
import com.eliceteam8.edupay.user.dto.UpdateUserDTO;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/academy")
public class AcademyController {

    private final AcademyService academyService;
    @GetMapping
    public ResponseEntity<AcademyCountDTO> getAcademyStudentLectureCount(){
        AcademyCountDTO studentAndLectureCount = academyService.getStudentAndLectureCount();
        return ResponseEntity.ok(studentAndLectureCount);
    }
}
