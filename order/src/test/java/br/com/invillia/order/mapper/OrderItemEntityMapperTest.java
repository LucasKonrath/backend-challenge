package br.com.invillia.order.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import br.com.invillia.order.domain.OrderItem;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;
import br.com.invillia.order.fixture.OrderEntityFixture;
import br.com.invillia.order.fixture.OrderItemFixture;

public class OrderItemEntityMapperTest {

    @Test
    public void ok() {

        final OrderItem item = OrderItemFixture.get().random().build();
        final OrderEntity entity = OrderEntityFixture.get().random().build();

        final OrderItemEntity actual = new OrderItemEntityMapper().apply(item, entity);
        assertEquals(actual.getDescription(), item.getDescription());
        assertSame(actual.getOrder(), entity);
        assertEquals(actual.getPrice(), item.getUnitPrice());
        assertEquals(actual.getQuantity(), item.getQuantity());

    }

    @Test
    public void ok_apply_all() {

        final OrderItem item1 = OrderItemFixture.get().random().build();

        final OrderItem item2 = OrderItemFixture.get().random().build();

        final List<OrderItem> items = asList(item1, item2);

        final OrderEntity entity = OrderEntityFixture.get().random().build();

        final List<OrderItemEntity> actual = new OrderItemEntityMapper().applyAll(items, entity);

        assertEquals(actual.get(0).getDescription(), item1.getDescription());
        assertSame(actual.get(0).getOrder(), entity);
        assertEquals(actual.get(0).getPrice(), item1.getUnitPrice());
        assertEquals(actual.get(0).getQuantity(), item1.getQuantity());

        assertEquals(actual.get(1).getDescription(), item2.getDescription());
        assertSame(actual.get(1).getOrder(), entity);
        assertEquals(actual.get(1).getPrice(), item2.getUnitPrice());
        assertEquals(actual.get(1).getQuantity(), item2.getQuantity());

    }

}
