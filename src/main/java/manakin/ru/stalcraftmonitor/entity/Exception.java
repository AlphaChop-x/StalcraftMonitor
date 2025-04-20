package manakin.ru.stalcraftmonitor.entity;

import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

@Setter
@Getter
public class Exception {
    private String message;
    private LocalDateTime recordedAt;
    private int code;

    private Exception(String message) {
        this.message = message;
    }

    public static Exception create(Throwable e) {
        return new Exception(e.getMessage());
    }

    public static Exception create(String message) {
        return new Exception(message);
    }
}
