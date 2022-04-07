package com.github.mawippel.services;

import com.github.mawippel.models.Product;
import com.github.mawippel.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(Long id) {
        return productRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Product not found.", Response.Status.NOT_FOUND));
    }

    public List<Product> findAll() {
        return productRepository.findAll().list();
    }

    @Transactional
    public Product insert(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public Product update(Long id, Product product) {
        var persistedProduct = findProduct(id);
        persistedProduct.setName(product.getName());
        persistedProduct.setPrice(product.getPrice());
        productRepository.persist(persistedProduct);
        return persistedProduct;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
