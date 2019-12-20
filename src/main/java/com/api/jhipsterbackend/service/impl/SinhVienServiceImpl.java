package com.api.jhipsterbackend.service.impl;

import com.api.jhipsterbackend.service.SinhVienService;
import com.api.jhipsterbackend.domain.SinhVien;
import com.api.jhipsterbackend.repository.SinhVienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SinhVien}.
 */
@Service
@Transactional
public class SinhVienServiceImpl implements SinhVienService {

    private final Logger log = LoggerFactory.getLogger(SinhVienServiceImpl.class);

    private final SinhVienRepository sinhVienRepository;

    public SinhVienServiceImpl(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    /**
     * Save a sinhVien.
     *
     * @param sinhVien the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SinhVien save(SinhVien sinhVien) {
        log.debug("Request to save SinhVien : {}", sinhVien);
        return sinhVienRepository.save(sinhVien);
    }

    /**
     * Get all the sinhViens.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SinhVien> findAll() {
        log.debug("Request to get all SinhViens");
        return sinhVienRepository.findAll();
    }


    /**
     * Get one sinhVien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SinhVien> findOne(Long id) {
        log.debug("Request to get SinhVien : {}", id);
        return sinhVienRepository.findById(id);
    }

    /**
     * Delete the sinhVien by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SinhVien : {}", id);
        sinhVienRepository.deleteById(id);
    }
}
