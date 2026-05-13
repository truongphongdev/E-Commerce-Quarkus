package com.phong.it.resource;

import com.phong.it.dto.request.OrderRequestDTO;
import com.phong.it.dto.response.OrderResponseDTO;
import com.phong.it.entity.OrderStatus;
import com.phong.it.service.OrderService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    public Response placeOrder(@HeaderParam("User-ID") Long userId, @Valid OrderRequestDTO requestDTO) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        OrderResponseDTO responseDTO = orderService.placeOrder(userId, requestDTO);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    public Response getOrderHistory(@HeaderParam("User-ID") Long userId) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        List<OrderResponseDTO> orders = orderService.getOrderHistory(userId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        OrderResponseDTO responseDTO = orderService.getOrderById(id);
        return Response.ok(responseDTO).build();
    }

    @PUT
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") OrderStatus status) {
        if (status == null) {
            throw new BadRequestException("Vui lòng cung cấp trạng thái (status) hợp lệ");
        }
        OrderResponseDTO responseDTO = orderService.updateStatus(id, status);
        return Response.ok(responseDTO).build();
    }
}
