package piscinas.com.api.domain.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record DtoUpdateExpense(
        Long id,
        Long customer_id,

        String description,

        LocalDateTime date,

       @PositiveOrZero(message = "O valor deve ser maior que zero")
        Double value
) {
}
