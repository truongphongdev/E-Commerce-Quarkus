package com.phong.it.resource;

import com.phong.it.dto.request.SupplierRequestDTO;
import com.phong.it.dto.response.SupplierResponseDTO;
import com.phong.it.service.SupplierService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierResource {

    @Inject
    SupplierService supplierService;

    @POST
    public Response create(@Valid SupplierRequestDTO requestDTO) {
        SupplierResponseDTO responseDTO = supplierService.create(requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        SupplierResponseDTO responseDTO = supplierService.getById(id);
        return Response.ok(responseDTO).build();
    }

    @GET
    public Response getAll() {
        List<SupplierResponseDTO> suppliers = supplierService.getAll();
        return Response.ok(suppliers).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid SupplierRequestDTO requestDTO) {
        SupplierResponseDTO responseDTO = supplierService.update(id, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        supplierService.delete(id);
        return Response.noContent().build();
    }
}
