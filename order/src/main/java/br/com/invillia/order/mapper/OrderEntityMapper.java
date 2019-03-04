package br.com.invillia.order.mapper;

import static java.util.Optional.ofNullable;

import java.util.function.Function;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.request.CreateOrderRequest;

public class OrderEntityMapper implements Function<CreateOrderRequest, OrderEntity> {

    @Override
    public OrderEntity apply(final CreateOrderRequest createOrderRequest) {

        final OrderEntity entity = new OrderEntity();

        ofNullable(createOrderRequest)
            .ifPresent(r -> {
                entity.setOrderStatus(OrderStatus.PENDING_PAYMENT);
                entity.setAddress(r.getAddress());
                entity.setConfirmationDate(createOrderRequest.getConfirmationDate());
            });

        return entity;
    }
}
