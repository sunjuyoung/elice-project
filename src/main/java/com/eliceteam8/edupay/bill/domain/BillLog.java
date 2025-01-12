package com.eliceteam8.edupay.bill.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill_log")
@Getter
@Setter
@NoArgsConstructor
public class BillLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "remaining_bills")
    private Long remainingBills; // 남은 발송 개수

    @CreationTimestamp
    // INSERT 쿼리 발생 시 현재 시간 값 적용
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @JsonBackReference
    private Bill bill;
}
