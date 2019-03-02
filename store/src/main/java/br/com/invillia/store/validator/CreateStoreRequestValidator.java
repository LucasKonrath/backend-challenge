package br.com.invillia.store.validator;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.request.CreateStoreRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreateStoreRequestValidator implements Consumer<CreateStoreRequest> {

    @Override
    public void accept(final CreateStoreRequest createStoreRequest) {

        if (isNull(createStoreRequest)) {
            log.warn("Informed store is null.");
            throw new BadRequestException("Informed store must not be null.");
        }

        if (isBlank(createStoreRequest.getAddress())) {
            log.warn("Invalid address informed: {}", createStoreRequest.getAddress());
            throw new BadRequestException("Informed store must have an address.");
        }

        if (isBlank(createStoreRequest.getName())) {
            log.warn("Invalid name informed: {}", createStoreRequest.getName());
            throw new BadRequestException("Informed store must have a name.");
        }

    }
}
