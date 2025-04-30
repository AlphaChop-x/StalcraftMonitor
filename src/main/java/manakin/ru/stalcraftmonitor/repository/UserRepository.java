package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    /**
     * Находит USER-а по его почте
     *
     * @param email почта пользователя
     */
    UserEntity findByEmailIgnoreCase(String email);

    /**
     * Находит все избранные предметы по почте юзера
     *
     * @param email почта пользователя
     */
    @Query("SELECT i.name FROM UserEntity u " +
            "JOIN u.favoriteItems i " +
            "WHERE u.userId = :udserID")
    List<String> findFavoriteItemsByUserEmail(@Param("Email") String email);

    @Query("SELECT COUNT(u) FROM UserEntity u")
    int countAll();
}
