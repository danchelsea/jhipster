package com.api.jhipsterbackend.service;

import com.api.jhipsterbackend.domain.Lop;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Lop}.
 */
public interface LopService {

    /**
     * Save a lop.
     *
     * @param lop the entity to save.
     * @return the persisted entity.
     */
    Lop save(Lop lop);

    /**
     * Get all the lops.
     *
     * @return the list of entities.
     */
    List<Lop> findAll();


    /**
     * Get the "id" lop.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Lop> findOne(Long id);

    /**
     * Delete the "id" lop.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
