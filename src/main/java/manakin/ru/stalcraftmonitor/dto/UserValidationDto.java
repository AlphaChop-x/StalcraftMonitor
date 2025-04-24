package manakin.ru.stalcraftmonitor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserValidationDto(
        @NotBlank(message = "Name is mandatory") String userName,
        @NotBlank(message = "Name is mandatory") String email,
        @NotBlank(message = "Password is mandatory") String password
) {
}
