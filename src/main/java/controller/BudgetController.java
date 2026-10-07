package com.example.expense_tracker.controller;

import com.example.expense_tracker.entity.Budget;
import com.example.expense_tracker.repository.BudgetRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "http://localhost:5173")
public class BudgetController {

    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @GetMapping("/{email}")
    public Budget getBudget(@PathVariable String email) {

        return budgetRepository.findByUserEmail(email).orElse(null);
    }

    @PostMapping
    public Budget saveBudget(@RequestBody Budget budget) {

        Budget existing =
                budgetRepository.findByUserEmail(budget.getUserEmail())
                        .orElse(null);

        if (existing != null) {
            existing.setAmount(budget.getAmount());
            return budgetRepository.save(existing);
        }

        return budgetRepository.save(budget);
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {

        budgetRepository.deleteById(id);
    }
}
