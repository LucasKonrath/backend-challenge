package br.com.invillia.order.response;

import java.io.Serializable;
import java.util.List;

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
public class SearchOrdersResponse implements Serializable {

    private static final long serialVersionUID = -8945999000646575758L;

    private final List<OrderResponse> orders;

}
