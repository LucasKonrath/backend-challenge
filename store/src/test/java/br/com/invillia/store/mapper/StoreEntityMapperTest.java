package br.com.invillia.store.mapper;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.request.CreateStoreRequest;

public class StoreEntityMapperTest {

    @Test
    void entity_empty_if_request_null() {
        final StoreEntity actual = new StoreEntityMapper().apply(null);
        assertNotNull(actual);
        assertNull(actual.getId());
        assertNull(actual.getAddress());
        assertNull(actual.getName());
    }

    @Test
    public void ok() {

        final CreateStoreRequest request = CreateStoreRequest.builder()
            .address(randomAlphabetic(20))
            .name(randomAlphabetic(20))
            .build();

        final StoreEntity actual = new StoreEntityMapper().apply(request);
        assertNotNull(actual);
        assertEquals(request.getAddress(), actual.getAddress());
        assertEquals(request.getName(), actual.getName());
    }

}
