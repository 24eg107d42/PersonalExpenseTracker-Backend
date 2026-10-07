package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

}