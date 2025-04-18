package manakin.ru.stalcraftmonitor.entity;

import jakarta.persistence.*;

import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
