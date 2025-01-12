package com.eliceteam8.edupay.get_cost.controller;

import com.eliceteam8.edupay.bill.domain.Status;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusRequestDto;
import com.eliceteam8.edupay.get_cost.dto.StudentPaymentStatusResponseDto;
import com.eliceteam8.edupay.get_cost.service.StudentPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class StudentPaymentStatusController {

    private final StudentPaymentStatusService studentPaymentStatusService;

    @Autowired
    public StudentPaymentStatusController(StudentPaymentStatusService studentPaymentStatusService) {
        this.studentPaymentStatusService = studentPaymentStatusService;
    }

    // 전체 수납 내역 조회
    @GetMapping
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getAllPayments(
            @RequestParam(name = "status", defaultValue = "ALL") Status status,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "studentName", required = false) String studentName,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(studentPaymentStatusService.getAllPayments(status, year, month, studentName, phoneNumber, page-1, size));
    }

    // 수납된 결제 내역 조회
    @GetMapping("/paid")
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getPaidPayments(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "studentName", required = false) String studentName,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(studentPaymentStatusService.getPaidPayments(year, month, studentName, phoneNumber, page-1, size));
    }

    // 미수납된 결제 내역 조회
    @GetMapping("/unpaid")
    public ResponseEntity<Page<StudentPaymentStatusResponseDto>> getUnpaidPayments(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "studentName", required = false) String studentName,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(studentPaymentStatusService.getUnpaidPayments(year, month, studentName, phoneNumber, page-1, size));
    }

    // 수납 상태 생성
    @PostMapping
    public ResponseEntity<StudentPaymentStatusResponseDto> createPaymentStatus(@RequestBody StudentPaymentStatusRequestDto requestDto) {
        return ResponseEntity.ok(studentPaymentStatusService.createPaymentStatus(requestDto));
    }

    // 수납 상태 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<StudentPaymentStatusResponseDto> updatePaymentStatus(@PathVariable Long id, @RequestBody StudentPaymentStatusRequestDto requestDto) {
        return ResponseEntity.ok(studentPaymentStatusService.updatePaymentStatus(id, requestDto));
    }

    // 수납 상태 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentStatus(@PathVariable Long id) {
        studentPaymentStatusService.deletePaymentStatus(id);
        return ResponseEntity.noContent().build();
    }

    // 단일 수납 상태 조회
    @GetMapping("/{id}")
    public ResponseEntity<StudentPaymentStatusResponseDto> getPaymentStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(studentPaymentStatusService.getPaymentStatusById(id));
    }
}
