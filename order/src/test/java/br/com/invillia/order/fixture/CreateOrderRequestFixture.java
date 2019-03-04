package br.com.invillia.order.fixture;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.time.LocalDate;
import java.util.List;

import br.com.invillia.order.domain.OrderItem;
import br.com.invillia.order.request.CreateOrderRequest;

public class CreateOrderRequestFixture {

    private final CreateOrderRequest.CreateOrderRequestBuilder builder = CreateOrderRequest.builder();

    public static CreateOrderRequestFixture get() {
        return new CreateOrderRequestFixture();
    }


    public CreateOrderRequestFixture address(final String address) {
        builder.address(address);
        return this;
    }

    public CreateOrderRequestFixture confirmationDate(final LocalDate confirmationDate) {
        builder.confirmationDate(confirmationDate);
        return this;
    }

    public CreateOrderRequestFixture items(final List<OrderItem> items){
        builder.items(items);
        return this;
    }

    public CreateOrderRequestFixture random() {
        return address(randomAlphabetic(9))
            .confirmationDate(LocalDate.now())
            .items(asList(OrderItemFixture.get().random().build(), OrderItemFixture.get().random().build() ));
    }

    public CreateOrderRequest build() {
        return builder.build();
    }

}
