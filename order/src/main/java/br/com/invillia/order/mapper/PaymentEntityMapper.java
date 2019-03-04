package br.com.invillia.order.mapper;

import static java.util.Optional.ofNullable;

import java.util.function.BiFunction;

import br.com.invillia.order.domain.PaymentStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.request.CreatePaymentRequest;

public class PaymentEntityMapper implements BiFunction<CreatePaymentRequest, OrderEntity, PaymentEntity> {

    @Override
    public PaymentEntity apply(final CreatePaymentRequest createPaymentRequest, final OrderEntity orderEntity) {

        final PaymentEntity entity = new PaymentEntity();

        ofNullable(createPaymentRequest)
            .ifPresent(r -> {
                entity.setCreditCardNumber(r.getCreditCardNumber());
                entity.setPaymentDate(r.getPaymentDate());
                entity.setStatus(PaymentStatus.PENDING);
                entity.setOrder(orderEntity);
            });

        return entity;
    }
}
