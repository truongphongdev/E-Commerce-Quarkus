package com.phong.it.resource;

import com.phong.it.dto.request.AddToCartRequestDTO;
import com.phong.it.dto.response.CartResponseDTO;
import com.phong.it.service.CartService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartService cartService;

    @GET
    public Response getCart(@HeaderParam("User-ID") Long userId) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        CartResponseDTO responseDTO = cartService.getCartByUserId(userId);
        return Response.ok(responseDTO).build();
    }

    @POST
    @Path("/items")
    public Response addToCart(@HeaderParam("User-ID") Long userId, @Valid AddToCartRequestDTO requestDTO) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        CartResponseDTO responseDTO = cartService.addToCart(userId, requestDTO);
        return Response.ok(responseDTO).build();
    }

    @PUT
    @Path("/items/{itemId}")
    public Response updateQuantity(
            @HeaderParam("User-ID") Long userId, 
            @PathParam("itemId") Long itemId, 
            @QueryParam("quantity") Integer quantity) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        if (quantity == null) {
            throw new BadRequestException("Vui lòng cung cấp tham số quantity (số lượng)");
        }
        CartResponseDTO responseDTO = cartService.updateQuantity(userId, itemId, quantity);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/items/{itemId}")
    public Response removeItem(@HeaderParam("User-ID") Long userId, @PathParam("itemId") Long itemId) {
        if (userId == null) {
            throw new BadRequestException("Vui lòng cung cấp User-ID trong Header");
        }
        CartResponseDTO responseDTO = cartService.removeItem(userId, itemId);
        return Response.ok(responseDTO).build();
    }
}
