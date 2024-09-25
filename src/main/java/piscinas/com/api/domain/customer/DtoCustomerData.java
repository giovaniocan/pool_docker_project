package piscinas.com.api.domain.customer;

public record DtoCustomerData(Long id, String name, String address) {
    public DtoCustomerData(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getAddress());
    }
}
