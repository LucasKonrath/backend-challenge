package br.com.invillia.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.mapper.StoreEntityMapper;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.request.CreateStoreRequest;
import br.com.invillia.store.validator.CreateStoreRequestValidator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreCreationService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CreateStoreRequestValidator validator;

    @Transactional
    public void createStore(final CreateStoreRequest request){

        validator.accept(request);

        log.info("Creating store: {}", request);
        final StoreEntity storeEntity = new StoreEntityMapper().apply(request);

        storeRepository.save(storeEntity);
    }
}
