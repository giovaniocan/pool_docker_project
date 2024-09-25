package piscinas.com.api.domain.expense;

import jakarta.persistence.*;
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
}
