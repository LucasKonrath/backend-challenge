package br.com.invillia.order.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.invillia.order.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequest implements Serializable {

    private static final long serialVersionUID = -1004845340648675418L;

    private final String address;

    private final LocalDate confirmationDate;

    private final List<OrderItem> items;

}
