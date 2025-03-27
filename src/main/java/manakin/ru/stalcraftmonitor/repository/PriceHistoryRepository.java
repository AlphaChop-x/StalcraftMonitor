package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.PriceHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends CrudRepository<PriceHistoryRepository, Long> {

    //Поиск истории цены для конкретного предмета по его имени
    @Query("SELECT ph FROM PriceHistory ph JOIN ph.item i WHERE i.name = :itemName")
    List<PriceHistory> findByItemName(@Param("itemName") String itemName);

    void delete(String itemName);

    //Сортировка по дате
    List<PriceHistoryRepository> findByItemOrderByRecordedAtDesc(Item item);
}
