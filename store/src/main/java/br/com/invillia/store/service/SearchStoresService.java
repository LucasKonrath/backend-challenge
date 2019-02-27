package br.com.invillia.store.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import br.com.invillia.store.entity.QStoreEntity;
import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.response.SearchStoreResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchStoresService {

    @Autowired
    private StoreRepository storeRepository;

    public SearchStoreResponse searchStores(final Long id, final String name, final String address) {

        final QStoreEntity entity = QStoreEntity.storeEntity;
        BooleanExpression predicate = Expressions.TRUE.eq(true);

        if (nonNull(id)) {
            predicate = predicate.and(entity.id.eq(id));
        }

        if (nonNull(name)) {
            predicate = predicate.and(entity.name.like("%".concat(name).concat("%")));
        }

        if (nonNull(address)) {
            predicate = predicate.and(entity.address.like("%".concat(address).concat("%")));
        }

        log.info("Searching stores for parameters: id {}, name {} and address {}", id, name, address);

        final List<StoreEntity> stores = StreamSupport.stream(storeRepository.findAll(predicate).spliterator(), false)
            .collect(Collectors.toList());

        return SearchStoreResponse.builder()
            .stores(stores)
            .build();
    }
}
