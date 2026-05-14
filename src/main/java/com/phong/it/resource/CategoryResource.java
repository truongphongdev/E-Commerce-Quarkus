package com.phong.it.resource;

import com.phong.it.dto.request.CategoryRequestDTO;
import com.phong.it.dto.response.CategoryResponseDTO;
import com.phong.it.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @POST
    public Response create(@Valid CategoryRequestDTO requestDTO) {
        CategoryResponseDTO responseDTO = categoryService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        CategoryResponseDTO responseDTO = categoryService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    public Response getAll() {
        List<CategoryResponseDTO> categories = categoryService.getAll();
        return Response.ok(categories).build();
    }

    @GET
    @Path("/roots")
    public Response getRootCategories() {
        List<CategoryResponseDTO> roots = categoryService.getRootCategories();
        return Response.ok(roots).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CategoryRequestDTO requestDTO) {
        CategoryResponseDTO responseDTO = categoryService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        categoryService.delete(id);
        return Response.noContent().build();
    }
}
