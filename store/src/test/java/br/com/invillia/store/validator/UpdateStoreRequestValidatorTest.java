package br.com.invillia.store.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.invillia.store.error.BadRequestException;
import br.com.invillia.store.fixture.UpdateStoreRequestFixture;
import br.com.invillia.store.request.UpdateStoreRequest;

@RunWith(MockitoJUnitRunner.class)
public class UpdateStoreRequestValidatorTest {

    @InjectMocks
    private UpdateStoreRequestValidator validator;

    @Test
    public void ok(){
        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().build();
        validator.accept(request);
    }

    @Test(expected = BadRequestException.class)
    public void request_null(){
        validator.accept(null);
    }

    @Test(expected = BadRequestException.class)
    public void request_id_null(){
        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().id(null).build();
        validator.accept(request);
    }

    @Test(expected = BadRequestException.class)
    public void request_address_blank(){
        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().address("").build();
        validator.accept(request);
    }

    @Test(expected = BadRequestException.class)
    public void request_name_blank(){
        final UpdateStoreRequest request = UpdateStoreRequestFixture.get().random().name("").build();
        validator.accept(request);
    }
}
