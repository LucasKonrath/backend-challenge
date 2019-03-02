package br.com.invillia.store.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.invillia.store.entity.StoreEntity;
import br.com.invillia.store.fixture.StoreEntityFixture;
import br.com.invillia.store.repository.StoreRepository;
import br.com.invillia.store.response.SearchStoreResponse;

@RunWith(MockitoJUnitRunner.class)
public class SearchStoresServiceTest {

    @InjectMocks
    private SearchStoresService searchStoresService;

    @Mock
    private StoreRepository storeRepository;

    @Captor
    private final ArgumentCaptor<BooleanExpression> predicateCaptor = ArgumentCaptor.forClass(BooleanExpression.class);

    private List<StoreEntity> entities;

    private StoreEntity store_1;
    private StoreEntity store_2;

    @Before
    public void setup(){
        entities = new ArrayList<>();

        store_1 = StoreEntityFixture.get().random().build();
        store_2 = StoreEntityFixture.get().random().build();

        entities.add(store_1);
        entities.add(store_2);
    }

    @Test
    public void ok(){

        final Long id = nextLong();
        final String name = randomAlphabetic(15);
        final String address = randomAlphabetic(20);

        when(storeRepository.findAll(any(com.querydsl.core.types.Predicate.class)))
            .thenReturn(new ArrayList<>(entities));

        final SearchStoreResponse actual = searchStoresService.searchStores(id, name, address);

        verify(storeRepository).findAll(predicateCaptor.capture());

        assertEquals(entities.size(), actual.getStores().size());

        assertTrue(predicateCaptor.getValue().toString().contains(".id = " + id));
        assertTrue(predicateCaptor.getValue().toString().contains(".name like %" + name + "%"));
        assertTrue(predicateCaptor.getValue().toString().contains(".address like %" + address + "%"));
    }
}
