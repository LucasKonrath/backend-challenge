package br.com.invillia.store.mapper;

import static java.util.Optional.ofNullable;

import java.util.function.Function;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.request.CreateStoreRequest;

public class StoreEntityMapper implements Function<CreateStoreRequest, StoreEntity> {

    @Override
    public StoreEntity apply(final CreateStoreRequest createStoreRequest) {

        final StoreEntity entity = new StoreEntity();

        ofNullable(createStoreRequest)
            .ifPresent(r -> {
                entity.setName(r.getName());
                entity.setAddress(r.getAddress());
            });

        return entity;
    }
}
