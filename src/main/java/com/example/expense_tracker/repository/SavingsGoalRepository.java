package com.example.expense_tracker.repository;

import com.example.expense_tracker.entity.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingsGoalRepository
        extends JpaRepository<SavingsGoal, Long> {

    Optional<SavingsGoal> findByUserEmail(String email);
}
