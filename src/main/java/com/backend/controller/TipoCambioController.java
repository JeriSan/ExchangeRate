package com.backend.controller;

import com.backend.dto.ExchangeRequest;
import com.backend.dto.Response;
import com.backend.entity.TipoCambioEntity;
import com.backend.services.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tipo-cambio")
public class TipoCambioController {
    @Autowired
    private TipoCambioService tipoCambioService;

    @PostMapping("/save")
    public Mono<TipoCambioEntity> save(@RequestBody TipoCambioEntity tipoCambioEntity){
        return tipoCambioService.save(tipoCambioEntity);
    }

    @GetMapping("/read/{id}")
    public Mono<TipoCambioEntity> getTipoCambioById(@PathVariable Long id){
        return tipoCambioService.getTipoCambioById(id);
    }

    @PutMapping("/update/{id}")
    public Mono<TipoCambioEntity> update (@PathVariable Long id, @RequestBody TipoCambioEntity tipoCambioEntity){
        return tipoCambioService.update(id, tipoCambioEntity.getTipoCambio());
    }

    @PostMapping("/calcular")
    public Mono<Response> calcularCambio(@RequestBody ExchangeRequest exchangeRequest){
        return tipoCambioService.calcularCambio(exchangeRequest);
    }


}
