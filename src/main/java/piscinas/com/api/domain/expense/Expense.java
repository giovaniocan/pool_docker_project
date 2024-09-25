package piscinas.com.api.domain.expense;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import piscinas.com.api.domain.customer.Customer;

import java.time.LocalDateTime;

@Table(name = "expense")
@Entity(name = "Expense")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String description;

    private Double value;

    private LocalDateTime date;

    public Expense(DtoAddExpense data, Customer customer) {
        this.customer = customer;
        this.description = data.description();
        this.value = data.value();
        this.date = data.date();
    }

    public void update(DtoUpdateExpense data, Customer customer) {
        this.customer = customer;
        if(data.description() != null) {
            this.description = data.description();
        }
        if(data.value() != null) {
            this.value = data.value();
        }
        if(data.date() != null) {
            this.date = data.date();
        }
    }
}
