package se.sprinto.hakan.demospringsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.sprinto.hakan.demospringsecurity.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}