package br.com.invillia.order.request;

import java.io.Serializable;
import java.time.LocalDate;

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
public class CreatePaymentRequest implements Serializable {

    private static final long serialVersionUID = -1004845340648675418L;

    private final Long orderId;

    private final String creditCardNumber;

    private final LocalDate paymentDate;

}
