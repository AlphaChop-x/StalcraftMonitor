package manakin.ru.stalcraftmonitor.Repositories;

import manakin.ru.stalcraftmonitor.Entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {
    List<Item> findByCategory(String category);
}
