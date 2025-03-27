package com.example.ProductMicro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setAvailable(product.getAvailable());
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) { productRepository.deleteById(id); }

    public boolean isProductAvailable(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(Product::getAvailable).orElse(false);
    }

}
