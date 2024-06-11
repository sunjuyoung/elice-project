package com.eliceteam8.edupay.user.controller;

import com.eliceteam8.edupay.user.dto.EmailDTO;
import com.eliceteam8.edupay.user.dto.PasswordTokenDTO;
import com.eliceteam8.edupay.user.dto.SignUpDTO;
import com.eliceteam8.edupay.user.dto.UserIdResponseDTO;
import com.eliceteam8.edupay.user.service.AuthService;
import com.eliceteam8.edupay.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<UserIdResponseDTO> signUp(@Valid @RequestBody SignUpDTO signUpDto ) {
        Long newUserId = authService.signUp(signUpDto);
        UserIdResponseDTO responseDTO = UserIdResponseDTO.builder()
                .userId(newUserId)
                .result("success")
                .build();
        return ResponseEntity.status(201).body(responseDTO);
    }


    @GetMapping("/check-email")
    public ResponseEntity<EmailDTO> checkEmailDuplicate(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        EmailDTO result = EmailDTO.builder().result(isDuplicate).build();
        return ResponseEntity.ok(result);
    }



    @PostMapping("/send-password-token")
    public ResponseEntity<EmailDTO> sendPasswordResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        boolean isSend =  userService.sendPasswordResetEmail(
                passwordTokenDTO.getEmail(),
                passwordTokenDTO.getUsername()
        );

        String message =  "인증번호를 발송했습니다. " +
                "인증번호가 오지 않으면 입력하신 정보가 회원정보와 일치하는지 확인해 주세요.";
        EmailDTO result = EmailDTO.builder().result(isSend).message(message).build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/check-password-token")
    public ResponseEntity<EmailDTO> checkResetEmail(@RequestBody PasswordTokenDTO passwordTokenDTO){
        if(passwordTokenDTO.getToken().isBlank()){
            throw new IllegalArgumentException("토큰값이 존재하지 않습니다.");
        }
        boolean result = userService.checkPasswordResetEmail(
                passwordTokenDTO.getToken(),
                passwordTokenDTO.getEmail()
        );
        EmailDTO emailDTO = EmailDTO.builder().result(result).build();
        return ResponseEntity.ok(emailDTO);
    }



}
