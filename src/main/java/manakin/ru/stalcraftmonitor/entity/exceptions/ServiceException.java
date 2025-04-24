package manakin.ru.stalcraftmonitor.entity.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceException extends Exception {
    private String message;
}
