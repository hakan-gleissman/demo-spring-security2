package se.sprinto.hakan.demospringsecurity.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.demospringsecurity.model.Product;
import se.sprinto.hakan.demospringsecurity.repository.ProductRepository;
import se.sprinto.hakan.demospringsecurity.util.ProductCategory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findAllShouldGroupProductsByCategory() {
        Product p1 = new Product("Laptop", new BigDecimal("9999"), ProductCategory.COMPUTERS);
        Product p2 = new Product("Mouse", new BigDecimal("199"), ProductCategory.COMPUTERS);

        Mockito.when(productRepository.findAll())
                .thenReturn(Arrays.asList(p1, p2));

        Map<String, List<Product>> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(ProductCategory.COMPUTERS, result.keySet().iterator().next());
        Mockito.verify(productRepository).findAll();
    }
}