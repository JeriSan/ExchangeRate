package com.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRequest {
    private Long idTipoCambio;
    private String usuario;
    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;

}