package br.com.invillia.order.service;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.fixture.OrderEntityFixture;
import br.com.invillia.order.fixture.OrderItemEntityFixture;
import br.com.invillia.order.fixture.PaymentEntityFixture;
import br.com.invillia.order.repository.OrderItemRepository;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.repository.PaymentRepository;
import br.com.invillia.order.response.SearchOrdersResponse;

@RunWith(MockitoJUnitRunner.class)
public class SearchOrdersServiceTest {

    @Captor
    private final ArgumentCaptor<BooleanExpression> predicateCaptor = ArgumentCaptor.forClass(BooleanExpression.class);
    @InjectMocks
    private SearchOrdersService searchOrdersService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private PaymentRepository paymentRepository;
    private List<OrderEntity> orderEntities;

    private OrderEntity order_1;
    private OrderEntity order_2;

    private PaymentEntity payment_1;
    private PaymentEntity payment_2;

    private OrderItemEntity item_1;
    private OrderItemEntity item_2;

    @Before
    public void setup() {
        orderEntities = new ArrayList<>();

        order_1 = OrderEntityFixture.get().random().build();
        order_2 = OrderEntityFixture.get().random().build();

        payment_1 = PaymentEntityFixture.get().random()
            .order(order_1)
            .build();

        payment_2 = PaymentEntityFixture.get().random()
            .order(order_2)
            .build();

        item_1 = OrderItemEntityFixture.get().random().build();
        item_2 = OrderItemEntityFixture.get().random().build();

        orderEntities.add(order_1);
        orderEntities.add(order_2);
    }

    @Test
    public void ok() {

        final Long id = nextLong();
        final String address = randomAlphabetic(20);
        final OrderStatus status = OrderStatus.PENDING_PAYMENT;
        final LocalDate initialConfirmationDate = LocalDate.now();
        final LocalDate finalConfirmationDate = LocalDate.now();

        when(orderRepository.findAll(any(com.querydsl.core.types.Predicate.class)))
            .thenReturn(new ArrayList<>(orderEntities));

        when(paymentRepository.findByOrder(order_1)).thenReturn(payment_1);
        when(paymentRepository.findByOrder(order_2)).thenReturn(payment_2);

        when(orderItemRepository.findAllByOrder(order_1)).thenReturn(asList(item_1));
        when(orderItemRepository.findAllByOrder(order_2)).thenReturn(asList(item_2));

        final SearchOrdersResponse actual = searchOrdersService.searchOrders(id, address, status,
            initialConfirmationDate, finalConfirmationDate);

        verify(orderRepository).findAll(predicateCaptor.capture());

        assertEquals(orderEntities.size(), actual.getOrders().size());

        assertEquals(actual.getOrders().get(0).getAddress(), order_1.getAddress());
        assertEquals(actual.getOrders().get(0).getConfirmationDate(), order_1.getConfirmationDate());
        assertEquals(actual.getOrders().get(0).getId(), order_1.getId());
        assertEquals(actual.getOrders().get(0).getOrderStatus(), order_1.getOrderStatus());

        assertEquals(actual.getOrders().get(1).getAddress(), order_2.getAddress());
        assertEquals(actual.getOrders().get(1).getConfirmationDate(), order_2.getConfirmationDate());
        assertEquals(actual.getOrders().get(1).getId(), order_2.getId());
        assertEquals(actual.getOrders().get(1).getOrderStatus(), order_2.getOrderStatus());

        assertEquals(actual.getOrders().get(0).getPayment().getCreditCardNumber(), payment_1.getCreditCardNumber());
        assertEquals(actual.getOrders().get(0).getPayment().getPaymentDate(), payment_1.getPaymentDate());
        assertEquals(actual.getOrders().get(0).getPayment().getStatus(), payment_1.getStatus());

        assertEquals(actual.getOrders().get(1).getPayment().getCreditCardNumber(), payment_2.getCreditCardNumber());
        assertEquals(actual.getOrders().get(1).getPayment().getPaymentDate(), payment_2.getPaymentDate());
        assertEquals(actual.getOrders().get(1).getPayment().getStatus(), payment_2.getStatus());

        assertEquals(actual.getOrders().get(0).getItems().get(0).getDescription(), item_1.getDescription());
        assertEquals(actual.getOrders().get(0).getItems().get(0).getQuantity(), item_1.getQuantity());
        assertEquals(actual.getOrders().get(0).getItems().get(0).getUnitPrice(), item_1.getPrice());
        assertEquals(actual.getOrders().get(0).getItems().get(0).getId(), item_1.getId());

        assertEquals(actual.getOrders().get(1).getItems().get(0).getDescription(), item_2.getDescription());
        assertEquals(actual.getOrders().get(1).getItems().get(0).getQuantity(), item_2.getQuantity());
        assertEquals(actual.getOrders().get(1).getItems().get(0).getUnitPrice(), item_2.getPrice());
        assertEquals(actual.getOrders().get(1).getItems().get(0).getId(), item_2.getId());

        assertTrue(predicateCaptor.getValue().toString().contains(".id = " + id));
        assertTrue(predicateCaptor.getValue().toString().contains(".address like %" + address + "%"));
        assertTrue(predicateCaptor.getValue().toString().contains(".orderStatus = " + status.name()));
        assertTrue(predicateCaptor.getValue().toString().contains(".confirmationDate < " + finalConfirmationDate));
        assertTrue(predicateCaptor.getValue().toString().contains(".confirmationDate > " + initialConfirmationDate));


    }
}
