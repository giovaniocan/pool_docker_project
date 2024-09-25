package piscinas.com.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
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
}
