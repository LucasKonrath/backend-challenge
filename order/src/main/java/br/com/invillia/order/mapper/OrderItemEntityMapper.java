package br.com.invillia.order.mapper;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import br.com.invillia.order.domain.OrderItem;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;

public class OrderItemEntityMapper implements BiFunction<OrderItem, OrderEntity, OrderItemEntity> {

    @Override
    public OrderItemEntity apply(final OrderItem orderItem, final OrderEntity orderEntity) {

        final OrderItemEntity entity = new OrderItemEntity();

        ofNullable(orderItem)
            .ifPresent(r -> {
                entity.setDescription(orderItem.getDescription());
                entity.setOrder(orderEntity);
                entity.setPrice(orderItem.getUnitPrice());
                entity.setQuantity(orderItem.getQuantity());
            });

        return entity;
    }

    public List<OrderItemEntity> applyAll(final List<OrderItem> items, final OrderEntity entity){

        return items.stream()
                .map(item ->apply(item, entity))
                .collect(Collectors.toList());

    }
}
