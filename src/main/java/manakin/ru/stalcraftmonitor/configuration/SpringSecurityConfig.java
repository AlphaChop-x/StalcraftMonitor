package manakin.ru.stalcraftmonitor.configuration;

import manakin.ru.stalcraftmonitor.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                        .requestMatchers("/swagger-ui/index.html").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated())//Для работы Unit тестов здесь требуется поставить .permitAll()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
