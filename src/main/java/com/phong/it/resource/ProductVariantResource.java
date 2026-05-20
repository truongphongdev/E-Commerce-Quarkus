package com.phong.it.resource;

import com.phong.it.dto.request.ProductVariantRequestDTO;
import com.phong.it.dto.response.ProductVariantResponseDTO;
import com.phong.it.service.ProductVariantService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/product-variants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductVariantResource {

    @Inject
    ProductVariantService productVariantService;

    @POST
    @RolesAllowed({"ADMIN"})
    public Response create(@Valid ProductVariantRequestDTO requestDTO) {
        ProductVariantResponseDTO responseDTO = productVariantService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") Long id) {
        ProductVariantResponseDTO responseDTO = productVariantService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    @PermitAll
    public Response getAll() {
        List<ProductVariantResponseDTO> variants = productVariantService.getAll();
        return Response.ok(variants).build();
    }

    @GET
    @Path("/product/{productId}")
    @PermitAll
    public Response getByProductId(@PathParam("productId") Long productId) {
        List<ProductVariantResponseDTO> variants = productVariantService.getByProductId(productId);
        return Response.ok(variants).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response update(@PathParam("id") Long id, @Valid ProductVariantRequestDTO requestDTO) {
        ProductVariantResponseDTO responseDTO = productVariantService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response delete(@PathParam("id") Long id) {
        productVariantService.delete(id);
        return Response.noContent().build();
    }
}
