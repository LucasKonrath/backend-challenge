package br.com.invillia.order.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 424921038149548126L;

    private final Long id;

    private final String description;

    private final BigDecimal unitPrice;

    private final Integer quantity;

}
