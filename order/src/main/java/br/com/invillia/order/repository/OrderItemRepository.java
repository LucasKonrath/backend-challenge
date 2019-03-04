package br.com.invillia.order.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.OrderItemEntity;

public interface OrderItemRepository extends Repository<OrderItemEntity, Long>, QuerydslPredicateExecutor<OrderItemEntity> {

    void save(OrderItemEntity orderItemEntity);

    OrderItemEntity findById(Long id);

    Iterable<OrderItemEntity> findAllByOrder(OrderEntity orderEntity);

    Iterable<OrderItemEntity> saveAll(Iterable<OrderItemEntity> items);
}
