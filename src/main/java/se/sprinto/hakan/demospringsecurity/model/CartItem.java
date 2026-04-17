package se.sprinto.hakan.demospringsecurity.model;

import java.math.BigDecimal;

public class CartItem {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public CartItem(Long productId, String productName, BigDecimal price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public BigDecimal getRowTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}