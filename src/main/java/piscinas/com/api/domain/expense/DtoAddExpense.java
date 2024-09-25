package piscinas.com.api.domain.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record DtoAddExpense(
        @NotNull(message = "O id do cliente é necessario")
        Long customer_id,

        @NotBlank(message = "A descrição da despesa é obrigatória")
        String description,

        @NotNull(message = "Data é obrigatória")
        LocalDateTime date,

        @NotNull(message = "O valor da despesa é obrigatório")
        @PositiveOrZero(message = "O valor deve ser maior que zero")
        Double value
) {
}
