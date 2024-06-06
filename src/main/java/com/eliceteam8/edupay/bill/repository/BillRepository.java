package com.eliceteam8.edupay.bill.repository;

import com.eliceteam8.edupay.bill.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findById(Long id);
}
