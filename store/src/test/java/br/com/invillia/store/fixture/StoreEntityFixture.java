package br.com.invillia.store.fixture;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;

import br.com.invillia.store.entity.StoreEntity;

public class StoreEntityFixture {

    private final StoreEntity entity;

    private StoreEntityFixture() {
        entity = new StoreEntity();
    }

    public static StoreEntityFixture get() {
        return new StoreEntityFixture();
    }

    public StoreEntityFixture id(final Long id) {
        entity.setId(id);
        return this;
    }

    public StoreEntityFixture address(final String address) {
        entity.setAddress(address);
        return this;
    }

    public StoreEntityFixture name(final String name) {
        entity.setName(name);
        return this;
    }

    public StoreEntityFixture random() {
        return id(nextLong())
            .name(randomAlphabetic(9))
            .address(randomAlphabetic(10));
    }

    public StoreEntity build() {
        return entity;
    }

}
