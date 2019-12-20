package com.api.jhipsterbackend.web.rest;

import com.api.jhipsterbackend.domain.SinhVien;
import com.api.jhipsterbackend.service.SinhVienService;
import com.api.jhipsterbackend.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.api.jhipsterbackend.domain.SinhVien}.
 */
@RestController
@RequestMapping("/api")
public class SinhVienResource {

    private final Logger log = LoggerFactory.getLogger(SinhVienResource.class);

    private static final String ENTITY_NAME = "sinhVien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SinhVienService sinhVienService;

    public SinhVienResource(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    /**
     * {@code POST  /sinh-viens} : Create a new sinhVien.
     *
     * @param sinhVien the sinhVien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sinhVien, or with status {@code 400 (Bad Request)} if the sinhVien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sinh-viens")
    public ResponseEntity<SinhVien> createSinhVien(@Valid @RequestBody SinhVien sinhVien) throws URISyntaxException {
        log.debug("REST request to save SinhVien : {}", sinhVien);
        if (sinhVien.getId() != null) {
            throw new BadRequestAlertException("A new sinhVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SinhVien result = sinhVienService.save(sinhVien);
        return ResponseEntity.created(new URI("/api/sinh-viens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sinh-viens} : Updates an existing sinhVien.
     *
     * @param sinhVien the sinhVien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sinhVien,
     * or with status {@code 400 (Bad Request)} if the sinhVien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sinhVien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sinh-viens")
    public ResponseEntity<SinhVien> updateSinhVien(@Valid @RequestBody SinhVien sinhVien) throws URISyntaxException {
        log.debug("REST request to update SinhVien : {}", sinhVien);
        if (sinhVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SinhVien result = sinhVienService.save(sinhVien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sinhVien.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sinh-viens} : get all the sinhViens.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sinhViens in body.
     */
    @GetMapping("/sinh-viens")
    public List<SinhVien> getAllSinhViens() {
        log.debug("REST request to get all SinhViens");
        return sinhVienService.findAll();
    }

    /**
     * {@code GET  /sinh-viens/:id} : get the "id" sinhVien.
     *
     * @param id the id of the sinhVien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sinhVien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sinh-viens/{id}")
    public ResponseEntity<SinhVien> getSinhVien(@PathVariable Long id) {
        log.debug("REST request to get SinhVien : {}", id);
        Optional<SinhVien> sinhVien = sinhVienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sinhVien);
    }

    /**
     * {@code DELETE  /sinh-viens/:id} : delete the "id" sinhVien.
     *
     * @param id the id of the sinhVien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sinh-viens/{id}")
    public ResponseEntity<Void> deleteSinhVien(@PathVariable Long id) {
        log.debug("REST request to delete SinhVien : {}", id);
        sinhVienService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
