package br.com.invillia.order.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.invillia.order.domain.OrderItem;
import br.com.invillia.order.domain.OrderStatus;
import br.com.invillia.order.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = -8945999000646575758L;

    private final Long id;

    private final String address;

    private final LocalDate confirmationDate;

    private final OrderStatus orderStatus;

    private final List<OrderItem> items;

    private final Payment payment;
 }
