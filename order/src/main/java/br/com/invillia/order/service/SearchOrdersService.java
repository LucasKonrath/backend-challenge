package br.com.invillia.order.service;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import br.com.invillia.order.domain.OrderItem;
import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.domain.Payment;
import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;
import br.com.invillia.order.entity.PaymentEntity;
import br.com.invillia.order.entity.QOrderEntity;
import br.com.invillia.order.repository.OrderItemRepository;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.repository.PaymentRepository;
import br.com.invillia.order.response.OrderResponse;
import br.com.invillia.order.response.SearchOrdersResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchOrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public SearchOrdersResponse searchOrders(final Long id, final String address, final OrderStatus status,
        final LocalDate initialConfirmationDate,
        final LocalDate finalConfirmationDate) {

        final QOrderEntity entity = QOrderEntity.orderEntity;
        BooleanExpression predicate = Expressions.TRUE.eq(true);

        if (nonNull(id)) {
            predicate = predicate.and(entity.id.eq(id));
        }

        if (nonNull(address)) {
            predicate = predicate.and(entity.address.like("%".concat(address).concat("%")));
        }

        if (nonNull(status)) {
            predicate = predicate.and(entity.orderStatus.eq(status));
        }

        if (nonNull(initialConfirmationDate)) {
            predicate = predicate.and(entity.confirmationDate.after(initialConfirmationDate));
        }

        if (nonNull(finalConfirmationDate)) {
            predicate = predicate.and(entity.confirmationDate.after(finalConfirmationDate));
        }

        log.info("Searching orders for parameters: id {}, address {}, status:{}", id, address, status);

        final List<OrderEntity> orders = StreamSupport.stream(orderRepository.findAll(predicate).spliterator(), false)
            .collect(Collectors.toList());

        final List<OrderResponse> orderResponses = orders
            .stream()
            .map(this::obterResponse)
            .collect(Collectors.toList());

        return SearchOrdersResponse.builder()
            .orders(orderResponses)
            .build();

    }

    private OrderResponse obterResponse(final OrderEntity orderEntity) {

        final PaymentEntity paymentEntity = paymentRepository.findByOrder(orderEntity);

        final Iterable<OrderItemEntity> itemEntities = orderItemRepository.findAllByOrder(orderEntity);

        final List<OrderItem> items = StreamSupport.stream(itemEntities.spliterator(), false)
            .map(i -> OrderItem.builder()
                    .description(i.getDescription())
                    .id(i.getId())
                    .quantity(i.getQuantity())
                    .unitPrice(i.getPrice())
                .build())
            .collect(Collectors.toList());

        return OrderResponse.builder()
            .address(orderEntity.getAddress())
            .confirmationDate(orderEntity.getConfirmationDate())
            .orderStatus(orderEntity.getOrderStatus())
            .payment(ofNullable(paymentEntity)
                .map(entity -> Payment.builder()
                    .creditCardNumber(entity.getCreditCardNumber())
                    .paymentDate(entity.getPaymentDate())
                    .status(entity.getStatus())
                    .build())
                .orElse(null)
            )
            .items(items)
            .build();
    }
}
