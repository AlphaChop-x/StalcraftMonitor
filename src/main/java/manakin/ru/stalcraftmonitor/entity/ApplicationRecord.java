package manakin.ru.stalcraftmonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "application_record")
public class ApplicationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'CREATED'")
    private RecordStatus status;

    @Column()
    private String recordContent;
}
