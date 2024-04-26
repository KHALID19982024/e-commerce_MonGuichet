package org.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long OrderItemsId;

//    @NotNull
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

//    @Positive
//    @NotNull
    @Column(name = "price", columnDefinition = "Decimal(10,2) default '0.00'", nullable = false)
    private BigDecimal price;

//    @NotNull
    //@Positive
    @Column(name = "quantity", columnDefinition = "Integer default 0", nullable = false)
    private Integer quantity;

    private BigDecimal subPrice;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "endedAt")
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var user = (OrderItems) o;
        return this.OrderItemsId.equals(user.getOrderItemsId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.OrderItemsId);
    }
}
