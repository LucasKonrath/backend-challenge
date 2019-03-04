package br.com.invillia.order.service;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.error.BadRequestException;
import br.com.invillia.order.mapper.PaymentEntityMapper;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.repository.PaymentRepository;
import br.com.invillia.order.request.CreatePaymentRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentCreationService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void createPayment(final CreatePaymentRequest request) {

        log.info("Creating payment: {}", request);
        final OrderEntity orderEntity = orderRepository.findById(request.getOrderId());

        if (isNull(orderEntity)) {
            log.warn("Order id: {} does not match an existing order.", request.getOrderId());
            throw new BadRequestException("Tried to create payment for non-existing order.");
        }

        final PaymentEntity paymentEntity = new PaymentEntityMapper().apply(request, orderEntity);
        paymentRepository.save(paymentEntity);
    }
}
