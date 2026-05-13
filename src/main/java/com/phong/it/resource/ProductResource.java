package com.phong.it.resource;

import com.phong.it.dto.request.ProductRequestDTO;
import com.phong.it.dto.response.ProductResponseDTO;
import com.phong.it.service.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    public Response create(@Valid ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        ProductResponseDTO responseDTO = productService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    public Response getAll() {
        List<ProductResponseDTO> products = productService.getAll();
        return Response.ok(products).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productService.delete(id);
        return Response.noContent().build();
    }
}
