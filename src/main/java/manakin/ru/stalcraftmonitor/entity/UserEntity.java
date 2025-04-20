package manakin.ru.stalcraftmonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "app_user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> favoriteItems;

    public List<Role> getRole() {
        return List.of(this.role);
    }

}
