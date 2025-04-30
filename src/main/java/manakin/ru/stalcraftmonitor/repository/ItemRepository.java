package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    List<Item> findByCategory(String category);

    /**
     * Удаляет предмет по его имени
     *
     * @param id айди предмета
     */
    void deleteItemById(String id);

    /**
     * Возвращает предмет по его ID
     *
     * @param itemId айди предмета
     */
    Item getItemById(String itemId);

    @Query("SELECT i FROM Item i")
    List<Item> findAllItems();

    @Query("SELECT i FROM Item i")
    int countAll();

}
