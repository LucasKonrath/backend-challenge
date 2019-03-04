package br.com.invillia.order.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import br.com.invillia.order.domain.PaymentStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.fixture.CreatePaymentRequestFixture;
import br.com.invillia.order.fixture.OrderEntityFixture;
import br.com.invillia.order.request.CreatePaymentRequest;

public class PaymentEntityMapperTest {

    @Test
    public void ok(){

        final CreatePaymentRequest from = CreatePaymentRequestFixture.get().random().build();

        final OrderEntity entity = OrderEntityFixture.get().random().build();

        final PaymentEntity actual = new PaymentEntityMapper().apply(from, entity);

       assertEquals(from.getCreditCardNumber(), actual.getCreditCardNumber());
       assertEquals(from.getPaymentDate(), actual.getPaymentDate());
       assertEquals(PaymentStatus.PENDING, actual.getStatus());
       assertSame(entity, actual.getOrder());
    }
}
