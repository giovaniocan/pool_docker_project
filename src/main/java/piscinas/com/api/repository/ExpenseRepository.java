package piscinas.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piscinas.com.api.domain.expense.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
