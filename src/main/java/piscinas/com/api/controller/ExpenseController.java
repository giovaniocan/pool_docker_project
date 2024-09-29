package piscinas.com.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import piscinas.com.api.domain.expense.DataDetailExpense;
import piscinas.com.api.domain.expense.DataListOfExpenseWithTotal;
import piscinas.com.api.domain.expense.DtoAddExpense;
import piscinas.com.api.domain.expense.DtoUpdateExpense;
import piscinas.com.api.services.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public ResponseEntity addExpense(@RequestBody @Valid DtoAddExpense data, UriComponentsBuilder uriBuilder){
        var expense = service.createExpense(data);


        var uri = uriBuilder.path("/expense/{id}").buildAndExpand(expense.id()).toUri();



        return ResponseEntity.created(uri).body(expense);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<DataListOfExpenseWithTotal> listExpensesByCustomer(
            @PathVariable Long id, @PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "year", required = false ) Integer year,
            @RequestParam(value = "month", required = false ) Integer month
            ) {

        Page<DataDetailExpense> page;

        if(year != null){

            if(month != null){
                 page =service.listAllByCustomerAndMonthAndYear(id, year, month, pageable);
            }

             page = service.listAllByCustomerAndYear(id, year, pageable);
        }else {
             page = service.listAllByCustomer(id, pageable);
        }

        Double total =  page.getContent().stream()
                .map(DataDetailExpense::value)
                .reduce(0.0, Double::sum);

        return ResponseEntity.ok(new DataListOfExpenseWithTotal(total, page));
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailExpense>> listExpenses(@PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable) {
        var page = service.listAll(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid DtoUpdateExpense data){
        var expenseUpdated = service.update(data);

        return ResponseEntity.ok(expenseUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailExpense> detailOneExpense(@PathVariable Long id){
        var expense = service.detailOne(id);

        return ResponseEntity.ok(expense);
    }

}
