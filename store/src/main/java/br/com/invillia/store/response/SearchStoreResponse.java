package br.com.invillia.store.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.invillia.store.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchStoreResponse implements Serializable {

    private static final long serialVersionUID = -8945999000646575758L;

    private final List<StoreEntity> stores;

}
