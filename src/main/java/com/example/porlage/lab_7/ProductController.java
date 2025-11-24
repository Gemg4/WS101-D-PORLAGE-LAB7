package com.example.porlage.lab_7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() { return this.productService.getProducts(); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable long id) { return this.productService.getProductById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) { return this.productService.addProduct(product.getName(), product.getPrice()); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable long id) { this.productService.deleteProductByid(id); }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProductById(@PathVariable long id, @RequestBody Product product) { this.productService.updateProductByid(id, product.getName(), product.getPrice()); }

}
