package com.phong.it.resource;

import java.util.HashMap;
import java.util.Map;

import com.phong.it.dto.request.PaymentRequestDTO;
import com.phong.it.dto.response.PaymentResponseDTO;
import com.phong.it.entity.Payment;
import com.phong.it.helper.ApiResponse;
import com.phong.it.mapper.PaymentMapper;
import com.phong.it.service.PaymentService;

import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
    @Inject
    PaymentService paymentService; // 💡 Quarkus tự động tìm nạp PaymentServiceImpl vào đây tại thời điểm chạy
                                   // runtime

    @Inject
    PaymentMapper paymentMapper;

    @Context
    HttpServerRequest request;

    @POST
    @Path("/create-vnpay")
    public Response createPaymentUrl(@Valid PaymentRequestDTO requestDTO) {
        // Tối ưu lấy IP khách hàng khi deploy qua Nginx/Proxy
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.remoteAddress().hostAddress();
        }

        // Gọi hàm qua lớp Interface một cách lỏng lẻo cực kỳ chuyên nghiệp
        String paymentUrl = paymentService.createPayment(requestDTO, ipAddress);

        return Response.ok(ApiResponse.success(Map.of("url", paymentUrl), "Tạo URL thanh toán thành công")).build();
    }

    @GET
    @Path("/vnpay-return")
    public Response vnpayReturn(@Context UriInfo uriInfo) {
        Map<String, String> queryParams = new HashMap<>();
        uriInfo.getQueryParameters().forEach((key, value) -> queryParams.put(key, value.get(0)));

        Payment resultPayment = paymentService.processReturn(queryParams);
        PaymentResponseDTO responseDTO = paymentMapper.toDto(resultPayment);

        return Response.ok(ApiResponse.success(responseDTO, "Xử lý kết quả thanh toán thành công")).build();
    }

    @GET
    @Path("/vnpay-ipn")
    public Response vnpayIpn(@Context UriInfo uriInfo) {
        try {
            Map<String, String> queryParams = new HashMap<>();
            uriInfo.getQueryParameters().forEach((key, value) -> queryParams.put(key, value.get(0)));

            // Gọi chung hàm xử lý logic
            paymentService.processReturn(queryParams);
            
            // Trả về đúng format VNPay yêu cầu để xác nhận đã nhận IPN thành công
            return Response.ok("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}").build();
        } catch (IllegalArgumentException e) {
            // VNPay quy định các mã lỗi IPN tương ứng (VD: 97 - Invalid Checksum, 04 - Invalid Amount)
            return Response.ok("{\"RspCode\":\"99\",\"Message\":\"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.ok("{\"RspCode\":\"99\",\"Message\":\"Unknown error\"}").build();
        }
    }
}
