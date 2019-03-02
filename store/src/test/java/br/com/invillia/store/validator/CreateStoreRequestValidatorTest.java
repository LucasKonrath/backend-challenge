package br.com.invillia.store.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.fixture.CreateStoreRequestFixture;
import br.com.invillia.store.request.CreateStoreRequest;

@RunWith(MockitoJUnitRunner.class)
public class CreateStoreRequestValidatorTest {

    @InjectMocks
    private CreateStoreRequestValidator createStoreRequestValidator;

    @Test
    public void ok(){

        final CreateStoreRequest createStoreRequest = CreateStoreRequestFixture.get().random().build();

        createStoreRequestValidator.accept(createStoreRequest);
    }

    @Test(expected = BadRequestException.class)
    public void request_null(){
        createStoreRequestValidator.accept(null);
    }

    @Test(expected = BadRequestException.class)
    public void request_address_blank(){
        final CreateStoreRequest createStoreRequest = CreateStoreRequestFixture.get().random().address("").build();
        createStoreRequestValidator.accept(createStoreRequest);
    }

    @Test(expected = BadRequestException.class)
    public void request_name_blank(){
        final CreateStoreRequest createStoreRequest = CreateStoreRequestFixture.get().random().name("").build();
        createStoreRequestValidator.accept(createStoreRequest);
    }
}
