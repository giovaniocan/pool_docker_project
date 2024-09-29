package piscinas.com.api.domain.expense;

import org.springframework.data.domain.Page;

import java.util.List;

public record DataListOfExpenseWithTotal(Double total, Page<DataDetailExpense> expenses) {
}
