package manakin.ru.stalcraftmonitor.Repositories;

import manakin.ru.stalcraftmonitor.Entities.Favorite;
import manakin.ru.stalcraftmonitor.Entities.Item;
import manakin.ru.stalcraftmonitor.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    //Поиск списка избранного по пользователю
    List<Favorite> findByUser(User user);

    //Поиск пользователей, добавивших предмет в избранное
    List<Favorite> findByItem(Item item);
}
