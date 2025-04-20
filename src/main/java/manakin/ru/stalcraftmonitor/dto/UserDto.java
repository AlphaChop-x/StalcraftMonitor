package manakin.ru.stalcraftmonitor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserDto(@NotBlank String userName,
                      @NotBlank String email,
                      @NotBlank String password
) {
}
