package se.sprinto.hakan.demospringsecurity.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private LocalDateTime orderDate;

    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {
    }

    public Order(String username, LocalDateTime orderDate, BigDecimal total, List<OrderItem> items) {
        this.username = username;
        this.orderDate = orderDate;
        this.total = total;
        this.items = items;

        for (OrderItem item : items) {
            item.setOrder(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String toMail() {
        StringBuilder builder = new StringBuilder();
        builder.append("Din order:").append("\n");
        for (OrderItem item : items) {
            builder.append(item.getProductName()).append("\n");
        }
        builder.append("Total pris: ").append(getTotal());

        return builder.toString();
    }
}