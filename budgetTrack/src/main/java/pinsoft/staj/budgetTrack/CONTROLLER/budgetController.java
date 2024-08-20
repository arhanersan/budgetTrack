package pinsoft.staj.budgetTrack.CONTROLLER;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pinsoft.staj.budgetTrack.ENTITY.budgetEntity;
import pinsoft.staj.budgetTrack.REPOSITORY.budgetRepository;
import pinsoft.staj.budgetTrack.SERVICE.budgetService;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@RestController
@RequestMapping("/api/budget")

public class budgetController {

    @Autowired
    private budgetRepository budgetRepository;

    public budgetController(budgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    budgetService budgetService;

    public void BudgetController(budgetService budgetService) {
        this.budgetService = budgetService;
    }


    @GetMapping("/all")
    public ResponseEntity getAllBudget() {
        return ResponseEntity.ok(this.budgetRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<budgetEntity> createBudgetEntry(@RequestBody budgetEntity budgetEntry) {
        budgetEntry.setDate(LocalDateTime.now());
        budgetEntity savedBudgetEntry = budgetRepository.save(budgetEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudgetEntry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<budgetEntity> updateBudgetEntry(@PathVariable Long id, @RequestBody budgetEntity budgetEntry) {
        budgetEntity existingBudgetEntry = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget entry not found with id: " + id));

        existingBudgetEntry.setDescription(budgetEntry.getDescription());
        existingBudgetEntry.setMoney(budgetEntry.getMoney());
        existingBudgetEntry.setType(budgetEntry.getType());
        existingBudgetEntry.setDate(now());

        budgetEntity updatedBudgetEntry = budgetRepository.save(existingBudgetEntry);
        return ResponseEntity.ok(updatedBudgetEntry);
    }

    @GetMapping("/search")public ResponseEntity<List<budgetEntity>> searchByDateRange(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate) {

        List<budgetEntity> results;
        if (startDate != null && endDate != null) {
            results = budgetRepository.findByDateBetween(startDate, endDate);
        } else if (startDate != null) {
            results = budgetRepository.findByDateAfter(startDate);
        } else if (endDate != null) {
            results = budgetRepository.findByDateBefore(endDate);
        } else {
            results = budgetRepository.findAll(); // Return all entries if no dates provided
        }

        return ResponseEntity.ok(results);
    }

}

