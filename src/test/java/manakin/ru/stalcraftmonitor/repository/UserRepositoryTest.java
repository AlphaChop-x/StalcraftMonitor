package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Тест для метода поиска имени юзера по его почте
     */

    @Test
    void findByEmailIgnoreCase() {
        String name = "SigmaSkibidi";
        String email = "sigmaSkibidi@gmail.com";

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        User foundUser = userRepository.findByEmailIgnoreCase(email);

        assertNotNull(foundUser);
        assertEquals(name, foundUser.getName());
        assertEquals(email, foundUser.getEmail());
    }
}