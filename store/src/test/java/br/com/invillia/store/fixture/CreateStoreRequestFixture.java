package br.com.invillia.store.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import br.com.invillia.store.request.CreateStoreRequest;

public class CreateStoreRequestFixture {

    private final CreateStoreRequest.CreateStoreRequestBuilder builder = CreateStoreRequest.builder();

    public static CreateStoreRequestFixture get() {
        return new CreateStoreRequestFixture();
    }


    public CreateStoreRequestFixture address(final String address) {
        builder.address(address);
        return this;
    }

    public CreateStoreRequestFixture name(final String name) {
        builder.name(name);
        return this;
    }

    public CreateStoreRequestFixture random() {
        return name(randomAlphabetic(9))
            .address(randomAlphabetic(10));
    }

    public CreateStoreRequest build() {
        return builder.build();
    }

}
