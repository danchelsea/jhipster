package com.api.jhipsterbackend.service;

import com.api.jhipsterbackend.domain.SinhVien;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SinhVien}.
 */
public interface SinhVienService {

    /**
     * Save a sinhVien.
     *
     * @param sinhVien the entity to save.
     * @return the persisted entity.
     */
    SinhVien save(SinhVien sinhVien);

    /**
     * Get all the sinhViens.
     *
     * @return the list of entities.
     */
    List<SinhVien> findAll();


    /**
     * Get the "id" sinhVien.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SinhVien> findOne(Long id);

    /**
     * Delete the "id" sinhVien.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
