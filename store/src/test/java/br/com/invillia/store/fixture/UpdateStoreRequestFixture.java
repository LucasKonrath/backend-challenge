package br.com.invillia.store.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import br.com.invillia.store.request.UpdateStoreRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateStoreRequestFixture {

    private final UpdateStoreRequest.UpdateStoreRequestBuilder builder = UpdateStoreRequest.builder();

    public static UpdateStoreRequestFixture get() {
        return new UpdateStoreRequestFixture();
    }

    public UpdateStoreRequestFixture id(final Long id) {
        builder.id(id);
        return this;
    }

    public UpdateStoreRequestFixture address(final String address) {
        builder.address(address);
        return this;
    }

    public UpdateStoreRequestFixture name(final String name) {
        builder.name(name);
        return this;
    }

    public UpdateStoreRequestFixture random() {
        return
            id(nextLong())
                .name(randomAlphabetic(9))
                .address(randomAlphabetic(10));
    }

    public UpdateStoreRequest build() {
        return builder.build();
    }

}
