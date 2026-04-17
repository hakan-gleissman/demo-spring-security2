package se.sprinto.hakan.demospringsecurity.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.demospringsecurity.model.Cart;
import se.sprinto.hakan.demospringsecurity.model.Order;
import se.sprinto.hakan.demospringsecurity.model.Product;
import se.sprinto.hakan.demospringsecurity.repository.OrderRepository;
import se.sprinto.hakan.demospringsecurity.util.ProductCategory;
import se.sprinto.hakan.springmessenger.service.MessageService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void checkoutShouldCreateOrderAndClearCart() {
        Cart cart = new Cart();
        cart.addProduct(new Product("Laptop", new BigDecimal("9999.00"),
                ProductCategory.COMPUTERS));
//        Order order = new Order();
//        when(orderRepository.save(order)).thenReturn(order);

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> {
                    Order order = invocation.getArgument(0);
                    return order;
                });
        Order result = orderService.checkout(cart, "hakan");

        assertNotNull(result);
        assertEquals("hakan", result.getUsername());
    }
}
