package se.sprinto.hakan.demospringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sprinto.hakan.demospringsecurity.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
