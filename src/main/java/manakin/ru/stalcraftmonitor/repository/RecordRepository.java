package manakin.ru.stalcraftmonitor.repository;

import manakin.ru.stalcraftmonitor.entity.ApplicationRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<ApplicationRecord, Integer> {

    @Query("SELECT ar.recordContent FROM ApplicationRecord ar WHERE ar.id = :id")
    String getApplicationRecordContentById(@Param("id") int id);

    @Query("SELECT ar FROM ApplicationRecord ar WHERE ar.id = :id")
    ApplicationRecord findById(@Param("id") int id);

    ApplicationRecord getApplicationRecordById(int id);
}
