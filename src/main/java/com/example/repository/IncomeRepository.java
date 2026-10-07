package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserEmail(String email);
}
