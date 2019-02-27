package br.com.invillia.store.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import br.com.invillia.store.entity.StoreEntity;

public interface StoreRepository extends Repository<StoreEntity, Long>, QuerydslPredicateExecutor<StoreEntity> {

    void save(StoreEntity storeEntity);

    StoreEntity findById(Long id);
}
