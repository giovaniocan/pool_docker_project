package piscinas.com.api.domain.customer;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "customer")
@Entity(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Boolean active;

    public Customer(DtoAddCustomer data) {
        this.name = data.name();
        this.address = data.address();
        this.active = true;
    }


    public void update( DtoUpdateCustomer data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.address() != null) {
            this.address = data.address();
        }
    }

    public void remove() {
        this.active = false;
    }
}
