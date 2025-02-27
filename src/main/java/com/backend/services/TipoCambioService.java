package com.backend.services;

import com.backend.dto.ExchangeRequest;
import com.backend.dto.Response;
import com.backend.entity.AuditoriaEntity;
import com.backend.entity.TipoCambioEntity;
import com.backend.repository.AuditoriaRepository;
import com.backend.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Transactional
    public Mono<TipoCambioEntity> save(TipoCambioEntity tipoCambioEntity){
        tipoCambioRepository.save(tipoCambioEntity);
        return getTipoCambioById(tipoCambioEntity.getId());
    }

    public Mono<TipoCambioEntity> getTipoCambioById (Long id){
        Optional<TipoCambioEntity> tipoCambioEntityOpt = tipoCambioRepository.findById(id);
        TipoCambioEntity tipoCambioEntity = tipoCambioEntityOpt.orElse(null);

        return Mono.just(tipoCambioEntity)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No se pudo encontrar el tipo de cambio: " + id)));
    }

    @Transactional
    public Mono<TipoCambioEntity> update(Long id, Double tipoCambio){
        tipoCambioRepository.update(id, tipoCambio);
        return getTipoCambioById(id);
    }


    public Mono<Response> calcularCambio(ExchangeRequest exchangeRequest){
        Optional<TipoCambioEntity> tipoCambioEntityOpt = tipoCambioRepository.findById(exchangeRequest.getIdTipoCambio());
        TipoCambioEntity tipoCambioEntity = tipoCambioEntityOpt.orElse(null);

        Double cambioCalculado = exchangeRequest.getMonto() * tipoCambioEntity.getTipoCambio();

        AuditoriaEntity auditoriaEntity = new AuditoriaEntity();
        auditoriaEntity.setMonto(exchangeRequest.getMonto());
        auditoriaEntity.setUsuario(exchangeRequest.getUsuario());
        auditoriaEntity.setFechaActual(LocalDateTime.now());
        auditoriaRepository.save(auditoriaEntity);

        Response response = new Response();
        response.setMontoCalculado(cambioCalculado);
        return Mono.just(response)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No se pudo calcular el cambio")));
    }
}
