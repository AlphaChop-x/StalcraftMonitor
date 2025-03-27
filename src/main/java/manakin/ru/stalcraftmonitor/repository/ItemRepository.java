package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    List<Item> findByCategory(String category);

    /**
     * Удаляети предмет по его имени
     *
     * @param name имя предмета
     */
    void deleteItemByName(String name);
}
