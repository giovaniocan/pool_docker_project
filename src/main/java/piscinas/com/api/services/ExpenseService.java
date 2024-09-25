package piscinas.com.api.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import piscinas.com.api.domain.expense.DataDetailExpense;
import piscinas.com.api.domain.expense.DtoAddExpense;
import piscinas.com.api.domain.expense.Expense;
import piscinas.com.api.repository.CustomerRepository;
import piscinas.com.api.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public DataDetailExpense createExpense(DtoAddExpense data) {

        var customer = customerRepository.findById(data.customer_id()).orElseThrow(() -> new RuntimeException("Cliente com id " + data.customer_id() + " não encontrado"));

        if (!customer.getActive()) {
            throw new HttpMessageNotReadableException("Cliente com id " + data.customer_id() + " não está ativo");
        }

        var expense = new Expense(data, customer);

        var newExpense = expenseRepository.save(expense);

        var detailExpense = new DataDetailExpense(newExpense);

        return detailExpense;
    }

}
