package br.com.invillia.store.validator;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.request.UpdateStoreRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UpdateStoreRequestValidator implements Consumer<UpdateStoreRequest> {

    @Override
    public void accept(final UpdateStoreRequest request) {

        if (isNull(request)) {
            log.warn("Informed store is null.");
            throw new BadRequestException("Informed store must not be null.");
        }

        if (isNull(request.getId())) {
            log.warn("Invalid id informed: {}", request.getId());
            throw new BadRequestException("Informed store must have an Id.");
        }

        if (isBlank(request.getAddress())) {
            log.warn("Invalid address informed: {}", request.getAddress());
            throw new BadRequestException("Informed store must have an address.");
        }

        if (isBlank(request.getName())) {
            log.warn("Invalid name informed: {}", request.getName());
            throw new BadRequestException("Informed store must have a name.");
        }

    }
}
