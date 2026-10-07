package com.example.expense_tracker.controller;

import com.example.expense_tracker.entity.Income;
import com.example.expense_tracker.repository.IncomeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "http://localhost:5173")
public class IncomeController {

    private final IncomeRepository incomeRepository;

    public IncomeController(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return incomeRepository.save(income);
    }

    @GetMapping("/{email}")
    public List<Income> getIncome(@PathVariable String email) {
        return incomeRepository.findByUserEmail(email);
    }

    @PutMapping("/{id}")
    public Income updateIncome(
            @PathVariable Long id,
            @RequestBody Income income) {

        Income existing = incomeRepository.findById(id).orElseThrow();

        existing.setAmount(income.getAmount());
        existing.setSource(income.getSource());

        return incomeRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {

        if (!incomeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        incomeRepository.deleteById(id);

        return ResponseEntity.ok("Income deleted successfully");
    }
}