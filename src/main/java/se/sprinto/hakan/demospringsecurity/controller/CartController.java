package se.sprinto.hakan.demospringsecurity.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.sprinto.hakan.demospringsecurity.model.Cart;
import se.sprinto.hakan.demospringsecurity.model.Product;
import se.sprinto.hakan.demospringsecurity.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = getOrCreateCart(session);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id);
        Cart cart = getOrCreateCart(session);
        cart.addProduct(product);
        return "redirect:/products";
    }

    private Cart getOrCreateCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }
}