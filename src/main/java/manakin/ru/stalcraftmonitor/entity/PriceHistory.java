package manakin.ru.stalcraftmonitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "price_history")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    public Item item;

    @Column(name = "price", columnDefinition = "DECIMAL(12,2)")
    private BigDecimal price;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
}
