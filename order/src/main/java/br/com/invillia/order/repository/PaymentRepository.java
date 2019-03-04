package br.com.invillia.order.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import br.com.invillia.order.entity.OrderEntity;
import br.com.invillia.order.entity.PaymentEntity;

public interface PaymentRepository extends Repository<PaymentEntity, Long>, QuerydslPredicateExecutor<PaymentEntity> {

    void save(PaymentEntity paymentEntity);

    PaymentEntity findById(Long id);

    PaymentEntity findByOrder(OrderEntity orderEntity);
}
