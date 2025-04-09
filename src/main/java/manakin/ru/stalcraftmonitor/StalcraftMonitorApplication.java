package manakin.ru.stalcraftmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("manakin.ru.stalcraftmonitor.repository")
@EntityScan("manakin.ru.stalcraftmonitor.entity")
public class StalcraftMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StalcraftMonitorApplication.class, args);
    }
}
