package piscinas.com.api.domain.customer;

public record DataListCustomer(Long id, String name, String address, Boolean active) {
    public DataListCustomer(Customer customer){
        this(customer.getId(), customer.getName(), customer.getAddress(), customer.getActive());
    }

}
