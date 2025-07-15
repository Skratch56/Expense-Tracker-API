package org.skratch.expensetracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private String title;

    private String amount;

    private LocalDate transactionDate;
}
