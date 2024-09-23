package piscinas.com.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
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
    public void addCustomer(@RequestBody @Valid DtoAddCustomer data) {
        service.createCostumer(data);
    }


    @GetMapping
    public Page<DataListCustomer> listar(@PageableDefault(size = 10, sort = {"name"})Pageable pageable) {
        return service.listAll(pageable);
    }

    @PutMapping
    public void update(@RequestBody @Valid DtoUpdateCustomer data) {
        service.updateCostumer(data);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        service.removeCostumer(id);
    }

}
