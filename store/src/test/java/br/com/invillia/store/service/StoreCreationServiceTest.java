package br.com.invillia.store.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

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
import br.com.invillia.store.fixture.CreateStoreRequestFixture;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.request.CreateStoreRequest;
import br.com.invillia.store.validator.CreateStoreRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class StoreCreationServiceTest {

    @Captor
    private final ArgumentCaptor<StoreEntity> entityCaptor = ArgumentCaptor.forClass(StoreEntity.class);
    @InjectMocks
    private StoreCreationService service;
    @Mock
    private StoreRepository repository;
    @Mock
    private CreateStoreRequestValidator validator;

    @Test
    public void assertAnnotation() {

        final Method createStoreMethod = ReflectionUtils.findMethod(service.getClass(), "createStore",
            CreateStoreRequest.class);

        assertTrue(createStoreMethod.isAnnotationPresent(Transactional.class));

    }

    @Test(expected = BadRequestException.class)
    public void validation_error() {
        doThrow(BadRequestException.class).when(validator).accept(any());

        final CreateStoreRequest request = CreateStoreRequestFixture.get().random().build();

        try {
            service.createStore(request);
        } finally {
            verify(validator).accept(request);
            verifyZeroInteractions(repository);
        }
    }

    @Test
    public void ok() {

        final CreateStoreRequest request = CreateStoreRequestFixture.get().random().build();

        service.createStore(request);

        verify(repository).save(entityCaptor.capture());

        assertEquals(request.getAddress(), entityCaptor.getValue().getAddress());
        assertEquals(request.getName(), entityCaptor.getValue().getName());
    }
}
