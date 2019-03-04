package br.com.invillia.order.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.request.CreateOrderRequest;
import br.com.invillia.order.request.CreatePaymentRequest;
import br.com.invillia.order.response.SearchOrdersResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("REST services related to operations involving orders.")
public interface OrderApi {

    @ApiOperation(value = "Create a order.",
        notes = "This operation creates a new order with the informed data.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Order created successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    void createOrder(@ApiParam(value = "Order to be created.",
        required = true) CreateOrderRequest createOrderRequest);

    @ApiOperation(value = "Create a payment for an existing order.",
        notes = "This operation creates a payment for the specified order.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Payment created successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    void createPayment(
        @ApiParam(value = "Payment to be created.",
            required = true) CreatePaymentRequest createPaymentRequest);

    @ApiOperation(value = "Search existing orders.",
        notes = "This operation retrieves the orders matching the informed data.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Orders retrieved successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    SearchOrdersResponse searchOrders(
        @ApiParam(value = "Order id to filter by.", required = false) Long id,
        @ApiParam(value = "Order address to filter by.", required = false) String address,
        @ApiParam(value = "Order status to filter by.", required = false) OrderStatus status,
        @ApiParam(value = "Order initial confirmation date to filter by.", required = false)
        @DateTimeFormat(iso = ISO.DATE) final LocalDate initialConfirmationDate,
        @ApiParam(value = "Order final confirmation date to filter by.", required = false)
        @DateTimeFormat(iso = ISO.DATE) final LocalDate finalConfirmationDate
    );

}
