package com.phong.it.resource;

import com.phong.it.dto.request.ProductRequestDTO;
import com.phong.it.dto.response.ProductResponseDTO;
import com.phong.it.service.ProductService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    @RolesAllowed({"ADMIN"})
    public Response create(@Valid ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") Long id) {
        ProductResponseDTO responseDTO = productService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    @PermitAll
    public Response getAll() {
        List<ProductResponseDTO> products = productService.getAll();
        return Response.ok(products).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response update(@PathParam("id") Long id, @Valid ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response delete(@PathParam("id") Long id) {
        productService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search")
    @PermitAll
    public Response search(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("keyword") String keyword,
            @QueryParam("categoryId") Long categoryId,
            @QueryParam("brand") String brand,
            @QueryParam("minPrice") java.math.BigDecimal minPrice,
            @QueryParam("maxPrice") java.math.BigDecimal maxPrice,
            @QueryParam("sortBy") String sortBy) {
        
        var result = productService.findWithFilters(
            page, size, keyword, categoryId, brand, minPrice, maxPrice, sortBy
        );
        return Response.ok(result).build();
    }
}
