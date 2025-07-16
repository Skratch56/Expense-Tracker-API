package org.skratch.expensetracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.skratch.expensetracker.dto.ExpenseDTO;
import org.skratch.expensetracker.model.Expense;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpensesMapper {

    Expense mapToExpense(ExpenseDTO expenseDTO);

    ExpenseDTO mapToExpenseDTO(Expense expense);

}
