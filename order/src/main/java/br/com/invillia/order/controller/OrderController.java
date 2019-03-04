package br.com.invillia.order.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.request.CreateOrderRequest;
import br.com.invillia.order.request.CreatePaymentRequest;
import br.com.invillia.order.response.SearchOrdersResponse;
import br.com.invillia.order.service.OrderCreationService;
import br.com.invillia.order.service.PaymentCreationService;
import br.com.invillia.order.service.SearchOrdersService;

@RestController
@RequestMapping
public class OrderController implements OrderApi {

    @Autowired
    private OrderCreationService orderCreationService;

    @Autowired
    private PaymentCreationService paymentCreationService;

    @Autowired
    private SearchOrdersService searchOrdersService;

    @Override
    @PostMapping
    public void createOrder(@RequestBody final CreateOrderRequest createOrderRequest) {
        orderCreationService.createOrder(createOrderRequest);
    }

    @Override
    @PostMapping("/payment")
    public void createPayment(@RequestBody final CreatePaymentRequest createPaymentRequest) {
        paymentCreationService.createPayment(createPaymentRequest);
    }

    @Override
    @GetMapping
    public SearchOrdersResponse searchOrders(
        @RequestParam(value = "Order id to filter by.", required = false) final Long id,
        @RequestParam(value = "Order address to filter by.", required = false) final String address,
        @RequestParam(value = "Order status to filter by.", required = false) final OrderStatus status,
        @RequestParam(value = "Order initial confirmation date to filter by.", required = false)
        @DateTimeFormat(iso = ISO.DATE) final LocalDate initialConfirmationDate,
        @RequestParam(value = "Order final confirmation date to filter by.", required = false)
        @DateTimeFormat(iso = ISO.DATE) final LocalDate finalConfirmationDate) {
        return searchOrdersService.searchOrders(id,
            address, status, initialConfirmationDate, finalConfirmationDate);
    }
}
