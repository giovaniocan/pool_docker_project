package piscinas.com.api.domain.customer;

import jakarta.validation.constraints.NotNull;

public record DtoUpdateCustomer(
        @NotNull
        Long id,
        String name,

        String address
) {
}
