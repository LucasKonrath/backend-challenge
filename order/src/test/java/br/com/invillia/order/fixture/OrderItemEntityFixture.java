package br.com.invillia.order.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.math.BigDecimal;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;

public class OrderItemEntityFixture {

    private final OrderItemEntity entity;

    private OrderItemEntityFixture() {
        entity = new OrderItemEntity();
    }

    public static OrderItemEntityFixture get() {
        return new OrderItemEntityFixture();
    }

    public OrderItemEntityFixture id(final Long id) {
        entity.setId(id);
        return this;
    }

    public OrderItemEntityFixture order(final OrderEntity order) {
        entity.setOrder(order);
        return this;
    }

    public OrderItemEntityFixture description(final String description) {
        entity.setDescription(description);
        return this;
    }

    public OrderItemEntityFixture price(final BigDecimal price) {
        entity.setPrice(price);
        return this;
    }

    public OrderItemEntityFixture quantity(final Integer quantity) {
        entity.setQuantity(quantity);
        return this;
    }

    public OrderItemEntityFixture random() {
        return id(nextLong())
            .order(OrderEntityFixture.get().random().build())
            .description(randomAlphabetic(20))
            .price(BigDecimal.valueOf(nextLong()))
            .quantity(nextInt());
    }

    public OrderItemEntity build() {
        return entity;
    }

}
