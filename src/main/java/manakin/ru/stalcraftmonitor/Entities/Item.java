package manakin.ru.stalcraftmonitor.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

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
