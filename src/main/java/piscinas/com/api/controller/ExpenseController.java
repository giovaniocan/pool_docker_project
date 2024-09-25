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
import piscinas.com.api.domain.expense.DtoAddExpense;
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

    @GetMapping
    public ResponseEntity<Page<DataDetailExpense>> listExpenses(@PageableDefault(size = 10, sort = {"date"},direction = Sort.Direction.DESC) Pageable pageable) {
        var page = service.listAll(pageable);

        return ResponseEntity.ok(page);
    }

}
