package se.sprinto.hakan.demospringsecurity.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product) {
        for (CartItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                item.increaseQuantity();
                return;
            }
        }

        items.add(new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                1
        ));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items) {
            total = total.add(item.getRowTotal());
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}