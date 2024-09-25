package piscinas.com.api.domain.customer;

import jakarta.validation.constraints.NotBlank;

public record DtoAddCustomer(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O endereço é obrigatório")
        String address
) {
}


