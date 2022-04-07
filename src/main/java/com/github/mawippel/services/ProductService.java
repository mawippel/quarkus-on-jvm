package com.github.mawippel.services;

import com.github.mawippel.models.Product;
import com.github.mawippel.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product insert(Product product) {
        productRepository.persist(product);
        return product;
    }

    public Product findProduct(Long id) {
        return productRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Product not found.", Response.Status.NOT_FOUND));
    }

}
