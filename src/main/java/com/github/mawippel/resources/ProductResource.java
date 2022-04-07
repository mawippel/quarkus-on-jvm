package com.github.mawippel.resources;

import com.github.mawippel.models.Product;
import com.github.mawippel.services.ProductService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Path("{id}")
    public Product get(@PathParam("id") Long productId) {
        return productService.findProduct(productId);
    }

    @POST
    public Product create(Product product) {
        return productService.insert(product);
    }
}