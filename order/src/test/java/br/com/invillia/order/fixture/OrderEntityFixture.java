package br.com.invillia.order.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.time.LocalDate;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.entity.OrderEntity;

public class OrderEntityFixture {

    private final OrderEntity entity;

    private OrderEntityFixture() {
        entity = new OrderEntity();
    }

    public static OrderEntityFixture get() {
        return new OrderEntityFixture();
    }

    public OrderEntityFixture id(final Long id) {
        entity.setId(id);
        return this;
    }

    public OrderEntityFixture address(final String address) {
        entity.setAddress(address);
        return this;
    }

    public OrderEntityFixture confirmationDate(final LocalDate confirmationDate) {
        entity.setConfirmationDate(confirmationDate);
        return this;
    }

    public OrderEntityFixture orderStatus(final OrderStatus orderStatus) {
        entity.setOrderStatus(orderStatus);
        return this;
    }

    public OrderEntityFixture random() {
        return id(nextLong())
            .address(randomAlphabetic(10))
            .confirmationDate(LocalDate.now())
            .orderStatus(OrderStatus.PENDING_PAYMENT);
    }

    public OrderEntity build() {
        return entity;
    }

}
