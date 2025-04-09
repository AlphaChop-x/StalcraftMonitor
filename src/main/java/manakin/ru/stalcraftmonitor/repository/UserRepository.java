package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    /**
     * Находит USER-а по его почте
     *
     * @param email почта пользователя
     */
    User findByEmailIgnoreCase(String email);

    /**
     * Находит все избранные предметы по почте юзера
     *
     * @param email почта пользователя
     */
    @Query("SELECT i.name FROM User u " +
            "JOIN u.favoriteItems i " +
            "WHERE u.userId = :udserID")
    List<String> findFavoriteItemsByUserEmail(@Param("Email") String email);
}
