package org.skratch.expensetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skratch.expensetracker.dto.ExpenseDTO;
import org.skratch.expensetracker.mapper.ExpensesMapper;
import org.skratch.expensetracker.model.Expense;
import org.skratch.expensetracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/expense-tracker")
@RequiredArgsConstructor
public class ExpenseTrackerController {

    private final ExpenseRepository expenseRepository;
    private final ExpensesMapper mapper;

    @Operation(
            summary = "Create a new expense",
            description = "Adds a new expense to the expense tracker repository."
    )
    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDto) {
        Expense expense = mapper.mapToExpense(expenseDto);
        expenseRepository.save(expense);
        log.info("Expense added to the expense tracker");
        return new ResponseEntity<>(mapper.mapToExpenseDTO(expense), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieves all expenses currently in the db",
            description = "Retrieves all expenses currently in the d"
    )
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(mapper::mapToExpenseDTO).toList();
        log.info("Retrieved all expenses from the db");
        return ResponseEntity.ok(expenseDTOS);
    }

    @Operation(
            summary = "Updates a expenses",
            description = "Updates an expense in the db based on Id"
    )
    @PutMapping("/{Id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO updatedExpense, @PathVariable String Id) {
        return expenseRepository.findById(Integer.parseInt(Id)).map(existing -> {
            existing.setAmount(updatedExpense.getAmount());
            existing.setTransactionDate(updatedExpense.getTransactionDate());
            existing.setTitle(updatedExpense.getTitle());
            Expense savedExpense = expenseRepository.save(existing);
            log.info("updated expense {} ", savedExpense);
            return new ResponseEntity<>(mapper.mapToExpenseDTO(savedExpense), HttpStatus.OK);
        }).orElseThrow(() -> new EntityNotFoundException("Expense not found"));
    }

    @Operation(
            summary = "Deletes an expense",
            description = "Deletes an expense in the db based on Id"
    )
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteExpense(String id) {
        expenseRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new EntityNotFoundException("Expense not found"));
        expenseRepository.deleteById(Integer.parseInt(id));
        log.info("deleted expense with id: {} ", id);
        return ResponseEntity.ok().build();
    }
}
