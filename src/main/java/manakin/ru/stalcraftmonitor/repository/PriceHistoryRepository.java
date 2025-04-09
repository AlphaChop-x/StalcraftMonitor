package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.PriceHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@Repository
public interface PriceHistoryRepository extends CrudRepository<PriceHistory, Long> {

    //Поиск истории цены для конкретного предмета по его имени
    @Query("SELECT ph FROM PriceHistory ph JOIN ph.item i WHERE i.id = :itemId")
    List<PriceHistory> findByItemId(@Param("itemId") String itemId);

    //Удаляет запись по id предмета
    void deleteByItemId(String itemId);

    //Сортировка по дате
    List<PriceHistoryRepository> findByItemOrderByRecordedAtDesc(Item item);
}
