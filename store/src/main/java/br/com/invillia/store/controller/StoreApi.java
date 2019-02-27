package br.com.invillia.store.controller;

import br.com.invillia.store.request.CreateStoreRequest;
import br.com.invillia.store.request.UpdateStoreRequest;
import br.com.invillia.store.response.SearchStoreResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("REST services related to operations involving stores.")
public interface StoreApi {

    @ApiOperation(value = "Create a new store.",
        notes = "This operation creates a new store with the informed data.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Store created successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    void createStore(@ApiParam(value = "Store to be created.",
        required = true) CreateStoreRequest createStoreRequest);

    @ApiOperation(value = "Update an existing store.",
        notes = "This operation updates an existing store with the informed data.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Store updated successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    void updateStore(@ApiParam(value = "Store to be updated.",
        required = true) UpdateStoreRequest updateStoreRequest);

    @ApiOperation(value = "Search existing stores.",
        notes = "This operation retrieves the stores matching the informed data.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Stores retrieved successfully."),
        @ApiResponse(code = 400, message = "Invalid parameters."),
        @ApiResponse(code = 401, message = "Unauthenticated user.")
    })
    SearchStoreResponse searchStores(
        @ApiParam(value = "Store id to filter by.", required = false) Long id,
        @ApiParam(value = "Store name to filter by.", required = false) String name,
        @ApiParam(value = "Store address to filter by.", required = false) String address);

}
