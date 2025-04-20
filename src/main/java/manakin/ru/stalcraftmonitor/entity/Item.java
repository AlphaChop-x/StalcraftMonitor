package manakin.ru.stalcraftmonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(nullable = false, length = 8)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "favoriteItems")
    private List<UserEntity> app_user;

    public Item(String id, String name, String category, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public Item() {

    }

}
