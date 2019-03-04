package br.com.invillia.order.domain;

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
public class Payment implements Serializable {

    private static final long serialVersionUID = 424921038149548126L;

    private final PaymentStatus status;

    private final String creditCardNumber;

    private final LocalDate paymentDate;

}
