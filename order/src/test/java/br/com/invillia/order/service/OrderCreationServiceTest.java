package br.com.invillia.order.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;
import br.com.invillia.order.fixture.CreateOrderRequestFixture;
import br.com.invillia.order.repository.OrderItemRepository;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.request.CreateOrderRequest;

@RunWith(MockitoJUnitRunner.class)
public class OrderCreationServiceTest {

    @Captor
    private final ArgumentCaptor<OrderEntity> orderEntityCaptor = ArgumentCaptor.forClass(OrderEntity.class);

    @Captor
    private final ArgumentCaptor<List<OrderItemEntity>> itemEntityCaptor = ArgumentCaptor.forClass(List.class);

    @InjectMocks
    private OrderCreationService service;

    @Mock
    private OrderRepository repository;

    @Mock
    private OrderItemRepository itemRepository;

    @Test
    public void assertAnnotation() {

        final Method createStoreMethod = ReflectionUtils.findMethod(service.getClass(), "createOrder",
            CreateOrderRequest.class);

        assertTrue(createStoreMethod.isAnnotationPresent(Transactional.class));

    }

    @Test
    public void ok() {

        final CreateOrderRequest request = CreateOrderRequestFixture.get().random().build();

        service.createOrder(request);

        verify(repository).save(orderEntityCaptor.capture());
        verify(itemRepository).saveAll(itemEntityCaptor.capture());
    }
}
