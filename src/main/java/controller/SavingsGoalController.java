package com.example.expense_tracker.controller;

import com.example.expense_tracker.entity.SavingsGoal;
import com.example.expense_tracker.repository.SavingsGoalRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savings")
@CrossOrigin(origins = "http://localhost:5173")
public class SavingsGoalController {

    private final SavingsGoalRepository repository;

    public SavingsGoalController(SavingsGoalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{email}")
    public SavingsGoal getGoal(@PathVariable String email) {
        return repository.findByUserEmail(email).orElse(null);
    }

    @PostMapping
    public SavingsGoal saveGoal(@RequestBody SavingsGoal goal) {

        SavingsGoal existing =
                repository.findByUserEmail(goal.getUserEmail())
                        .orElse(null);

        if (existing != null) {
            existing.setGoalName(goal.getGoalName());
            existing.setTargetAmount(goal.getTargetAmount());
            existing.setSavedAmount(goal.getSavedAmount());

            return repository.save(existing);
        }

        return repository.save(goal);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
