package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Favorite;
import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    //Поиск списка избранного по пользователю
    List<Favorite> findByUser(User user);

    //Поиск пользователей, добавивших предмет в избранное
    List<Favorite> findByItemName(String itemName);

    /**
     * Удаление всех записей в избранное у всех пользователей
     *
     * @param itemName имя предмета
     */
    void deleteAllByItemName(String itemName);
}
