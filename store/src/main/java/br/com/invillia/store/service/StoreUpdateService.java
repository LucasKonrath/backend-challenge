package br.com.invillia.store.service;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.request.UpdateStoreRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreUpdateService {

    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    public void updateStore(final UpdateStoreRequest updateStoreRequest){

        log.info("Updating store: {}", updateStoreRequest);
        final StoreEntity storeEntity = storeRepository.findById(updateStoreRequest.getId());

        if(isNull(storeEntity)){
            log.warn("Informed store has not been found: {}", updateStoreRequest);
            throw new BadRequestException();
        }

        storeEntity.setName(updateStoreRequest.getName());
        storeEntity.setAddress(updateStoreRequest.getAddress());

        storeRepository.save(storeEntity);
    }
}
