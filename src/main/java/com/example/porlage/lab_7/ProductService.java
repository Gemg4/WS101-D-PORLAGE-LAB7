package com.example.porlage.lab_7;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class ProductService {
    private static final List<Product> products = new ArrayList<>();
    private static long productCount = 0;
    static {
        products.add(new Product(++productCount, "paper", 20.00));
        products.add(new Product(++productCount, "pencil", 11.50));
        products.add(new Product(++productCount, "eraser", 25.99));
    }

    public List<Product> getProducts() { return products; }

    public Product getProductById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Resources not found"));
    }

    public Product addProduct(String name, double price){
        return addProduct(++productCount, name, price);
    }

    public Product addProduct(long id, String name, double price) {
        Product product = new Product(id, name, price);
        products.add(product);
        return product;
    }

    public void deleteProductByid(long id) {
        products.remove(products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Resource not found")));
    }

    public void updateProductByid(long id, String name, double price) {
        Product product = products.stream()
                .filter(product1 -> product1.getId() == id).
                findFirst()
                .map(product1 -> {
                    product1.setName(name.isEmpty()? product1.getName() : name);
                    product1.setPrice(price);
                    return product1;
                })
                .orElseThrow(() -> new NullPointerException("Resource not found"));
        deleteProductByid(id);
        addProduct(product.getId(), product.getName(), product.getPrice());
    }
}
