package com.api.jhipsterbackend.web.rest;

import com.api.jhipsterbackend.domain.Lop;
import com.api.jhipsterbackend.service.LopService;
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
 * REST controller for managing {@link com.api.jhipsterbackend.domain.Lop}.
 */
@RestController
@RequestMapping("/api")
public class LopResource {

    private final Logger log = LoggerFactory.getLogger(LopResource.class);

    private static final String ENTITY_NAME = "lop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LopService lopService;

    public LopResource(LopService lopService) {
        this.lopService = lopService;
    }

    /**
     * {@code POST  /lops} : Create a new lop.
     *
     * @param lop the lop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lop, or with status {@code 400 (Bad Request)} if the lop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lops")
    public ResponseEntity<Lop> createLop(@Valid @RequestBody Lop lop) throws URISyntaxException {
        log.debug("REST request to save Lop : {}", lop);
        if (lop.getId() != null) {
            throw new BadRequestAlertException("A new lop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Lop result = lopService.save(lop);
        return ResponseEntity.created(new URI("/api/lops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lops} : Updates an existing lop.
     *
     * @param lop the lop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lop,
     * or with status {@code 400 (Bad Request)} if the lop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lops")
    public ResponseEntity<Lop> updateLop(@Valid @RequestBody Lop lop) throws URISyntaxException {
        log.debug("REST request to update Lop : {}", lop);
        if (lop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Lop result = lopService.save(lop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lops} : get all the lops.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lops in body.
     */
    @GetMapping("/lops")
    public List<Lop> getAllLops() {
        log.debug("REST request to get all Lops");
        return lopService.findAll();
    }

    /**
     * {@code GET  /lops/:id} : get the "id" lop.
     *
     * @param id the id of the lop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lops/{id}")
    public ResponseEntity<Lop> getLop(@PathVariable Long id) {
        log.debug("REST request to get Lop : {}", id);
        Optional<Lop> lop = lopService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lop);
    }

    /**
     * {@code DELETE  /lops/:id} : delete the "id" lop.
     *
     * @param id the id of the lop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lops/{id}")
    public ResponseEntity<Void> deleteLop(@PathVariable Long id) {
        log.debug("REST request to delete Lop : {}", id);
        lopService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
