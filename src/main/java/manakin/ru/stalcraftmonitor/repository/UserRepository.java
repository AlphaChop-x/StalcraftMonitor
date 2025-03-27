package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    /**
     * Находит USER-а по его почте
     * @param email почта пользователя*/
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
