package br.com.invillia.store.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.fixture.StoreEntityFixture;
import br.com.invillia.store.fixture.UpdateStoreRequestFixture;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.request.UpdateStoreRequest;
import br.com.invillia.store.validator.UpdateStoreRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class StoreUpdateServiceTest {

    @Captor
    private final ArgumentCaptor<StoreEntity> entityCaptor = ArgumentCaptor.forClass(StoreEntity.class);

    @InjectMocks
    private StoreUpdateService service;

    @Mock
    private UpdateStoreRequestValidator validator;

    @Mock
    private StoreRepository repository;

    @Test
    public void assertAnnotation() {

        final Method createStoreMethod = ReflectionUtils.findMethod(service.getClass(), "updateStore",
            UpdateStoreRequest.class);

        assertTrue(createStoreMethod.isAnnotationPresent(Transactional.class));

    }


    @Test(expected = BadRequestException.class)
    public void validation_error() {

        doThrow(BadRequestException.class).when(validator).accept(any());

        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().build();

        try {
            service.updateStore(request);
        } finally {
            verify(validator).accept(request);
            verifyZeroInteractions(repository);
        }
    }

    @Test(expected = BadRequestException.class)
    public void error_non_existing_store() {

        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().build();

        try {
            service.updateStore(request);
        } finally {

            verify(repository).findById(request.getId());
            verify(repository, never()).save(entityCaptor.capture());
        }
    }

    public void ok() {

        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().build();

        final StoreEntity storeEntity = StoreEntityFixture.get()
            .random()
            .id(request.getId())
            .build();

        when(repository.findById(request.getId())).thenReturn(storeEntity);

        service.updateStore(request);

        verify(repository).findById(request.getId());

        verify(repository).save(entityCaptor.capture());

        assertEquals(request.getId(), entityCaptor.getValue().getId());
        assertEquals(request.getAddress(), entityCaptor.getValue().getAddress());
        assertEquals(request.getName(), entityCaptor.getValue().getName());
    }
}
