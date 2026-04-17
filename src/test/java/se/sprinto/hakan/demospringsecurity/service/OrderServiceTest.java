package se.sprinto.hakan.demospringsecurity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.sprinto.hakan.demospringsecurity.model.Cart;
import se.sprinto.hakan.demospringsecurity.model.Order;
import se.sprinto.hakan.demospringsecurity.model.Product;
import se.sprinto.hakan.demospringsecurity.repository.OrderRepository;
import se.sprinto.hakan.demospringsecurity.util.ProductCategory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService checkoutService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCheckout() {
        Cart cart = new Cart();
        Product product = new Product("TV", new BigDecimal(10), ProductCategory.MONITORS);
        cart.addProduct(product);
        Order order = checkoutService.checkout(cart, "hakan.gleissman@gmail.com");

        assertEquals(new BigDecimal(10), order.getTotal());
        assertEquals(1, orderRepository.findAll().size());

    }
}
