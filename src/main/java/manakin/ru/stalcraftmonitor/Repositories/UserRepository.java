package manakin.ru.stalcraftmonitor.Repositories;

import manakin.ru.stalcraftmonitor.Entities.Item;
import manakin.ru.stalcraftmonitor.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByEmailIgnoreCase(String email);

    /**
     * Находит все избранные предметы пользователя
     * @param username имя пользователя
     */

    @Query(value = "SELECT i FROM User u " +
            "JOIN Favorite f ON u.id = f.user " +
            "JOIN Item i ON f.item = i.id " +
            "WHERE u.name = :userName")
    List<Item> findFavoriteItemsByUsername(@Param("username") String username);
}
