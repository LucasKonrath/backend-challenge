package br.com.invillia.order.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.time.LocalDate;

import br.com.invillia.order.request.CreatePaymentRequest;

public class CreatePaymentRequestFixture {

    private final CreatePaymentRequest.CreatePaymentRequestBuilder builder = CreatePaymentRequest.builder();

    public static CreatePaymentRequestFixture get() {
        return new CreatePaymentRequestFixture();
    }


    public CreatePaymentRequestFixture orderId(final Long orderId) {
        builder.orderId(orderId);
        return this;
    }

    public CreatePaymentRequestFixture creditCardNumber(final String creditCardNumber) {
        builder.creditCardNumber(creditCardNumber);
        return this;
    }

    public CreatePaymentRequestFixture paymentDate(final LocalDate paymentDate){
        builder.paymentDate(paymentDate);
        return this;
    }

    public CreatePaymentRequestFixture random() {
        return orderId(nextLong())
            .creditCardNumber(randomNumeric(11))
            .paymentDate(LocalDate.now());
    }

    public CreatePaymentRequest build() {
        return builder.build();
    }

}
