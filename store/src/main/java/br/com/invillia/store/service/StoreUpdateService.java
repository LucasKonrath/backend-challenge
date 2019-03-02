package br.com.invillia.store.service;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.request.UpdateStoreRequest;
import br.com.invillia.store.validator.UpdateStoreRequestValidator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreUpdateService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UpdateStoreRequestValidator validator;

    @Transactional
    public void updateStore(final UpdateStoreRequest request){

        validator.accept(request);

        log.info("Updating store: {}", request);
        final StoreEntity storeEntity = storeRepository.findById(request.getId());

        if(isNull(storeEntity)){
            log.warn("Informed store has not been found: {}", request);
            throw new BadRequestException("Informed Id does not correspond to an existing store.");
        }

        storeEntity.setName(request.getName());
        storeEntity.setAddress(request.getAddress());

        storeRepository.save(storeEntity);
    }
}
