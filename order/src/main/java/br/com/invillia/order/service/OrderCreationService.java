package br.com.invillia.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;
import br.com.invillia.order.mapper.OrderEntityMapper;
import br.com.invillia.order.mapper.OrderItemEntityMapper;
import br.com.invillia.order.repository.OrderItemRepository;
import br.com.invillia.order.repository.OrderRepository;
import br.com.invillia.order.request.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderCreationService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public void createOrder(final CreateOrderRequest request){

        log.info("Creating order: {}", request);

        final OrderEntity orderEntity = new OrderEntityMapper().apply(request);
        orderRepository.save(orderEntity);

        final List<OrderItemEntity> items = new OrderItemEntityMapper().applyAll(request.getItems(), orderEntity);
        orderItemRepository.saveAll(items);
    }
}
