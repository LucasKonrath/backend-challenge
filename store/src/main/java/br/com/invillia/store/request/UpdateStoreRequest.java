package br.com.invillia.store.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStoreRequest implements Serializable {

    private static final long serialVersionUID = -1004845340648675418L;

    private final Long id;

    private final String address;

    private final String name;
}
