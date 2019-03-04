package br.com.invillia.order.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

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
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.error.BadRequestException;
import br.com.invillia.order.fixture.CreatePaymentRequestFixture;
import br.com.invillia.order.fixture.OrderEntityFixture;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.repository.PaymentRepository;
import br.com.invillia.order.request.CreatePaymentRequest;

@RunWith(MockitoJUnitRunner.class)
public class PaymentCreationServiceTest {

    @Captor
    private final ArgumentCaptor<PaymentEntity> entityCaptor = ArgumentCaptor.forClass(PaymentEntity.class);

    @InjectMocks
    private PaymentCreationService service;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void assertAnnotation() {

        final Method createStoreMethod = ReflectionUtils.findMethod(service.getClass(), "createPayment",
            CreatePaymentRequest.class);

        assertTrue(createStoreMethod.isAnnotationPresent(Transactional.class));

    }

    @Test(expected = BadRequestException.class)
    public void order_not_found() {

        final CreatePaymentRequest request = CreatePaymentRequestFixture.get().random().build();

        try{
            service.createPayment(request);
        }finally {
            verify(orderRepository).findById(request.getOrderId());
            verifyZeroInteractions(paymentRepository);
        }
    }

    @Test
    public void ok(){
        final CreatePaymentRequest request = CreatePaymentRequestFixture.get().random().build();

        final OrderEntity mockEntity = OrderEntityFixture.get().random().build();

        when(orderRepository.findById(request.getOrderId())).thenReturn(mockEntity);

        service.createPayment(request);

        verify(orderRepository).findById(request.getOrderId());
        verify(paymentRepository).save(entityCaptor.capture());

        assertEquals(mockEntity, entityCaptor.getValue().getOrder());
        assertEquals(request.getCreditCardNumber(), entityCaptor.getValue().getCreditCardNumber());
        assertEquals(request.getPaymentDate(), entityCaptor.getValue().getPaymentDate());
    }
}
