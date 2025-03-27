package manakin.ru.stalcraftmonitor;

import manakin.ru.stalcraftmonitor.entity.User;
import manakin.ru.stalcraftmonitor.repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StalcraftMonitorApplicationTests {

    private final UserRepository userRepository;

    @Autowired
    public StalcraftMonitorApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void testFindUserByEmail() {
        String name = "Тестовичок";
        String email = "test@test.com";

        //Создание пользователя с именем и почтой
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        User foundUser = userRepository.findByEmailIgnoreCase("test@test.com");

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getName(), foundUser.getName());
        Assertions.assertEquals(name, foundUser.getName());
    }
}