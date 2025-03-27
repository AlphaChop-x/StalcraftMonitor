package manakin.ru.stalcraftmonitor.Repositories;

import manakin.ru.stalcraftmonitor.Entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceHistory extends CrudRepository<PriceHistory, Long> {

    //Поиск истории цены для конкретного предмета
    List<PriceHistory> findByItem(Item item);

    //Сортировка по дате
    List<PriceHistory> findByItemOrderByRecordedAtDesc(Item item);
}
