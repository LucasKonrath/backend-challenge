package br.com.invillia.order.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.fixture.CreateOrderRequestFixture;
import br.com.invillia.order.request.CreateOrderRequest;

public class OrderEntityMapperTest {

    @Test
    public void ok(){
        final CreateOrderRequest from = CreateOrderRequestFixture.get().random().build();

        final OrderEntity actual = new OrderEntityMapper().apply(from);

        assertEquals(from.getAddress(), actual.getAddress());
        assertEquals(from.getConfirmationDate(), actual.getConfirmationDate());
        assertEquals(OrderStatus.PENDING_PAYMENT, actual.getOrderStatus());
    }
}
