package piscinas.com.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import piscinas.com.api.domain.expense.Expense;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE e.customer.id = :id")
    Page<Expense> findByCustomerId(Long id, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.customer.id = :id AND YEAR(e.date) = :year")
    Page<Expense> findByCustomerIdAndYear(Long id, Integer year,  Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.customer.id = :id AND YEAR(e.date) = :year AND MONTH(e.date) = :month")
    Page<Expense> findByCustomerIdAndYearAndMonth(Long id, Integer year, Integer month, Pageable pageable);
}
