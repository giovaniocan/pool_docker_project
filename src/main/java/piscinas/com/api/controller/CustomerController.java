package piscinas.com.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import piscinas.com.api.domain.customer.Customer;
import piscinas.com.api.domain.customer.DataListCustomer;
import piscinas.com.api.domain.customer.DtoAddCustomer;
import piscinas.com.api.domain.customer.DtoUpdateCustomer;
import piscinas.com.api.services.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<DataListCustomer> addCustomer(@RequestBody @Valid DtoAddCustomer data, UriComponentsBuilder uriBuilder) {
        var customer = service.createCostumer(data);

        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.id()).toUri();

        return ResponseEntity.created(uri).body(customer);
    }

    @GetMapping
    public ResponseEntity<Page<DataListCustomer>> list(@PageableDefault(size = 10, sort = {"name"})Pageable pageable) {
        var page = service.listAll(pageable);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid DtoUpdateCustomer data) {

        var updatedCustomer = service.updateCostumer(data);
        System.out.println("---------------------" + updatedCustomer);
        return  ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        service.removeCostumer(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity datailCustomer(@PathVariable Long id) {
        var customer = service.getCustomer(id);

        return ResponseEntity.ok(customer);
    }

}
