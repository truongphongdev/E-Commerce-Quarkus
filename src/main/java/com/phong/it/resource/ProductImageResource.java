package com.phong.it.resource;

import com.phong.it.dto.request.ProductImageRequestDTO;
import com.phong.it.dto.response.ProductImageResponseDTO;
import com.phong.it.service.ProductImageService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/product-images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductImageResource {

    @Inject
    ProductImageService productImageService;

    @POST
    @RolesAllowed({"ADMIN"})
    public Response create(@Valid ProductImageRequestDTO requestDTO) {
        ProductImageResponseDTO responseDTO = productImageService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") Long id) {
        ProductImageResponseDTO responseDTO = productImageService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    @PermitAll
    public Response getAll() {
        List<ProductImageResponseDTO> images = productImageService.getAll();
        return Response.ok(images).build();
    }

    @GET
    @Path("/product/{productId}")
    @PermitAll
    public Response getByProductId(@PathParam("productId") Long productId) {
        List<ProductImageResponseDTO> images = productImageService.getByProductId(productId);
        return Response.ok(images).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response update(@PathParam("id") Long id, @Valid ProductImageRequestDTO requestDTO) {
        ProductImageResponseDTO responseDTO = productImageService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response delete(@PathParam("id") Long id) {
        productImageService.delete(id);
        return Response.noContent().build();
    }
}
