package piscinas.com.api.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import piscinas.com.api.domain.expense.DataDetailExpense;
import piscinas.com.api.domain.expense.DtoAddExpense;
import piscinas.com.api.domain.expense.DtoUpdateExpense;
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

    public Page<DataDetailExpense> listAll(Pageable pageable) {
        return expenseRepository.findAll(pageable).map(DataDetailExpense::new);
    }

    @Transactional
    public DataDetailExpense update(@Valid DtoUpdateExpense data) {
        if(!expenseRepository.existsById(data.id())){
            throw new RuntimeException("Despesa com id " + data.id() + " não encontrada");
        }

        if(!customerRepository.existsById(data.customer_id())){
            throw new RuntimeException("Cliente com id " + data.customer_id() + " não encontrado");
        }

        var expense = expenseRepository.getReferenceById(data.id());
        var customer = expense.getCustomer();

        if(!data.customer_id().equals(expense.getCustomer().getId())){
            customer = customerRepository.getReferenceById(data.customer_id());
        }

        expense.update(data, customer);

        return new DataDetailExpense(expense);
    }

    @Transactional
    public void delete(Long id) {
        var expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa com id " + id + " não encontrada"));

        expenseRepository.deleteById(id);
    }
}
