package br.com.invillia.order.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import br.com.invillia.order.entity.OrderEntity;

public interface OrderRepository extends Repository<OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {

    void save(OrderEntity orderEntity);

    OrderEntity findById(Long id);
}
