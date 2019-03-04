package br.com.invillia.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.invillia.store.request.CreateStoreRequest;
import br.com.invillia.store.request.UpdateStoreRequest;
import br.com.invillia.store.response.SearchStoreResponse;
import br.com.invillia.store.service.SearchStoresService;
import br.com.invillia.store.service.StoreCreationService;
import br.com.invillia.store.service.StoreUpdateService;

@RestController
@RequestMapping
public class StoreController implements StoreApi {

    @Autowired
    private StoreCreationService storeCreationService;

    @Autowired
    private StoreUpdateService storeUpdateService;

    @Autowired
    private SearchStoresService searchStoresService;

    @Override
    @PostMapping
    public void createStore(@RequestBody final CreateStoreRequest createStoreRequest) {
        storeCreationService.createStore(createStoreRequest);
    }

    @Override
    @PutMapping
    public void updateStore(@RequestBody final UpdateStoreRequest updateStoreRequest) {
        storeUpdateService.updateStore(updateStoreRequest);
    }

    @Override
    @GetMapping
    public SearchStoreResponse searchStores(
        @RequestParam(value = "id", required = false) final Long id,
        @RequestParam(value = "name", required = false) final String name,
        @RequestParam(value = "address", required = false) final String address) {
        return searchStoresService.searchStores(id, name, address);
    }
}
