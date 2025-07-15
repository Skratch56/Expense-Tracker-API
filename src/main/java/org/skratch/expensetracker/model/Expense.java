package org.skratch.expensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Expense {
    @Id
    private Long id;

}
