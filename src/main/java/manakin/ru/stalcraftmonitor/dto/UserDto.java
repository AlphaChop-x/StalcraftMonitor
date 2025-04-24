package manakin.ru.stalcraftmonitor.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(UUID id, String email, String username) {
}
