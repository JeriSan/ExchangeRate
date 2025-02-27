package com.backend.repository;

import com.backend.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TipoCambioRepository extends CrudRepository<TipoCambioEntity, Long> {
  @Modifying
  @Query("Update TipoCambioEntity tc set tc.tipoCambio = :tipoCambio where tc.id = :id")
  void update(Long id, Double tipoCambio);
}
