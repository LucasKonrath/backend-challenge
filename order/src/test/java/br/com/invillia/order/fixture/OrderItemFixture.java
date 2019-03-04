package br.com.invillia.order.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.math.BigDecimal;

import br.com.invillia.order.domain.OrderItem;

public class OrderItemFixture {

    private final OrderItem.OrderItemBuilder builder = OrderItem.builder();

    public static OrderItemFixture get() {
        return new OrderItemFixture();
    }


    public OrderItemFixture id(final Long id) {
        builder.id(id);
        return this;
    }

    public OrderItemFixture description(final String description) {
        builder.description(description);
        return this;
    }

    public OrderItemFixture unitPrice(final BigDecimal unitPrice){
        builder.unitPrice(unitPrice);
        return this;
    }

    public OrderItemFixture quantity(final Integer quantity){
        builder.quantity(quantity);
        return this;
    }

    public OrderItemFixture random() {
        return id(nextLong())
            .description(randomAlphabetic(20))
            .quantity(nextInt())
            .unitPrice(BigDecimal.valueOf(nextLong()));
    }

    public OrderItem build() {
        return builder.build();
    }

}
