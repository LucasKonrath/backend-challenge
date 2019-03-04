package br.com.invillia.order.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import java.time.LocalDate;

import br.com.invillia.order.domain.PaymentStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.PaymentEntity;

public class PaymentEntityFixture {

    private final PaymentEntity entity;

    private PaymentEntityFixture() {
        entity = new PaymentEntity();
    }

    public static PaymentEntityFixture get() {
        return new PaymentEntityFixture();
    }

    public PaymentEntityFixture id(final Long id) {
        entity.setId(id);
        return this;
    }

    public PaymentEntityFixture order(final OrderEntity order) {
        entity.setOrder(order);
        return this;
    }

    public PaymentEntityFixture status(final PaymentStatus status) {
        entity.setStatus(status);
        return this;
    }

    public PaymentEntityFixture creditCardNumber(final String creditCardNumber) {
        entity.setCreditCardNumber(creditCardNumber);
        return this;
    }

    public PaymentEntityFixture paymentDate(final LocalDate paymentDate) {
        entity.setPaymentDate(paymentDate);
        return this;
    }

    public PaymentEntityFixture random() {
        return id(nextLong())
            .order(OrderEntityFixture.get().random().build())
            .status(PaymentStatus.PENDING)
            .creditCardNumber(randomNumeric(11))
            .paymentDate(LocalDate.now());
    }

    public PaymentEntity build() {
        return entity;
    }

}
