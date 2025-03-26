package manakin.ru.stalcraftmonitor.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "favorite")
public class Favorite {
    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime added_at;

    @Embeddable
    public class FavoriteId implements Serializable {
        private UUID user_id;
        private String item_id;
    }


}
