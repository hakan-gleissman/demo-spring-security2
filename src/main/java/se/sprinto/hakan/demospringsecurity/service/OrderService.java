package se.sprinto.hakan.demospringsecurity.service;

import org.springframework.stereotype.Service;
import se.sprinto.hakan.demospringsecurity.model.Cart;
import se.sprinto.hakan.demospringsecurity.model.Order;
import se.sprinto.hakan.demospringsecurity.model.OrderItem;
import se.sprinto.hakan.demospringsecurity.repository.OrderRepository;
import se.sprinto.hakan.springmessenger.model.Email;
import se.sprinto.hakan.springmessenger.service.MessageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MessageService messageService;

    public OrderService(OrderRepository orderRepository, MessageService messageService) {
        this.orderRepository = orderRepository;
        this.messageService = messageService;
    }

    public Order checkout(Cart cart, String username) {
        //validera
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Kundvagnen är tom eller saknas");
        }
        List<OrderItem> items = cart.getItems().stream()
                .map(item -> new OrderItem(
                        item.getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                ))
                .toList();
        Order order = new Order(
                username,
                LocalDateTime.now(),
                cart.getTotal(),
                items
        );

        Order savedOrder = orderRepository.save(order);

        cart.clear();
        Email email = new Email();
        email.setRecipient(username);
        email.setSubject("Din order");
        email.setMessage(savedOrder.toMail());
        messageService.send(email);

        return savedOrder;
    }

}
