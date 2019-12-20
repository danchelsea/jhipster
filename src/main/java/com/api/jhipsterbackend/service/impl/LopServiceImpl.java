package com.api.jhipsterbackend.service.impl;

import com.api.jhipsterbackend.service.LopService;
import com.api.jhipsterbackend.domain.Lop;
import com.api.jhipsterbackend.repository.LopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Lop}.
 */
@Service
@Transactional
public class LopServiceImpl implements LopService {

    private final Logger log = LoggerFactory.getLogger(LopServiceImpl.class);

    private final LopRepository lopRepository;

    public LopServiceImpl(LopRepository lopRepository) {
        this.lopRepository = lopRepository;
    }

    /**
     * Save a lop.
     *
     * @param lop the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Lop save(Lop lop) {
        log.debug("Request to save Lop : {}", lop);
        return lopRepository.save(lop);
    }

    /**
     * Get all the lops.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Lop> findAll() {
        log.debug("Request to get all Lops");
        return lopRepository.findAll();
    }


    /**
     * Get one lop by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Lop> findOne(Long id) {
        log.debug("Request to get Lop : {}", id);
        return lopRepository.findById(id);
    }

    /**
     * Delete the lop by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lop : {}", id);
        lopRepository.deleteById(id);
    }
}
