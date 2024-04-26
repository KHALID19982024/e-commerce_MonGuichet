package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.Enum.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

//    @Column(name = "order_number", nullable = false)
//    private String orderNumber;

    @Column(name = "total_price", columnDefinition = "Decimal(10,2) default '0.00'", nullable = false)
    private BigDecimal totalPrice = BigDecimal.valueOf(0);

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "VARCHAR(20) default 'PENDING'", nullable = false)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    @Column(name = "order_date",nullable = false, updatable = false)
    private Date orderDate;


    public void increaseTotalPrice(BigDecimal value) {
        setTotalPrice(this.totalPrice.add(value));
    }

    public void addItem(OrderItems orderItem) {
        orderItems.add(orderItem);
    }

    public void removeItem(Long itemId) {
        this.orderItems.removeIf(item -> item.getOrderItemsId().equals(itemId));
    }

}

