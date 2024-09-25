package piscinas.com.api.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import piscinas.com.api.domain.customer.*;
import piscinas.com.api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Transactional
    public DataListCustomer createCostumer(DtoAddCustomer data) {
        var customer = new Customer(data);
        var newCustomer = repository.save(customer);

        var detailCustomer = new DataListCustomer(newCustomer);


        return detailCustomer;
    }

    public Page<DataListCustomer> listAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DataListCustomer::new);
    }


    @Transactional
    public DtoCustomerData updateCostumer(@Valid DtoUpdateCustomer data) {
        var costumer = repository.getReferenceById(data.id());
        costumer.update(data);
        return new DtoCustomerData(costumer);
    }


    @Transactional
    public void removeCostumer(Long id) {
        var costumer = repository.getReferenceById(id);
        costumer.remove();
    }

    public DtoCustomerData getCustomer(Long id) {
        var customer = repository.getReferenceById(id);
        return  new DtoCustomerData(customer);
    }
}
