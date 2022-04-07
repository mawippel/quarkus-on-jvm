package com.github.mawippel.resources;

import com.github.mawippel.models.Product;
import com.github.mawippel.services.ProductService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    public List<Product> list() {
        return productService.findAll();
    }

    @POST
    public Product create(Product product) {
        return productService.insert(product);
    }

    @PUT
    @Path("{id}")
    public Product update(@PathParam("id") Long id, Product product) {
        return productService.update(id, product);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        productService.delete(id);
        return Response.ok().build();
    }
}