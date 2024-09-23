package piscinas.com.api.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import piscinas.com.api.domain.customer.Customer;
import piscinas.com.api.domain.customer.DataListCustomer;
import piscinas.com.api.domain.customer.DtoAddCustomer;
import piscinas.com.api.domain.customer.DtoUpdateCustomer;
import piscinas.com.api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Transactional
    public void createCostumer(DtoAddCustomer data) {
        System.out.println(data);
        repository.save(new Customer(data));
    }

    public Page<DataListCustomer> listAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DataListCustomer::new);
    }


    @Transactional
    public void updateCostumer(@Valid DtoUpdateCustomer data) {
        var costumer = repository.getReferenceById(data.id());
        costumer.update(data);
    }


    @Transactional
    public void removeCostumer(Long id) {
        var costumer = repository.getReferenceById(id);
        costumer.remove();
    }
}
