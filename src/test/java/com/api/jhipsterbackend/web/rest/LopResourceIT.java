package com.api.jhipsterbackend.web.rest;

import com.api.jhipsterbackend.BackendApp;
import com.api.jhipsterbackend.domain.Lop;
import com.api.jhipsterbackend.repository.LopRepository;
import com.api.jhipsterbackend.service.LopService;
import com.api.jhipsterbackend.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.api.jhipsterbackend.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LopResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class LopResourceIT {

    private static final String DEFAULT_MA_LOP = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOP = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LOP = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOP = "BBBBBBBBBB";

    private static final Integer DEFAULT_SI_SO = 1;
    private static final Integer UPDATED_SI_SO = 2;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private LopService lopService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restLopMockMvc;

    private Lop lop;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LopResource lopResource = new LopResource(lopService);
        this.restLopMockMvc = MockMvcBuilders.standaloneSetup(lopResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lop createEntity(EntityManager em) {
        Lop lop = new Lop()
            .maLop(DEFAULT_MA_LOP)
            .tenLop(DEFAULT_TEN_LOP)
            .siSo(DEFAULT_SI_SO);
        return lop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lop createUpdatedEntity(EntityManager em) {
        Lop lop = new Lop()
            .maLop(UPDATED_MA_LOP)
            .tenLop(UPDATED_TEN_LOP)
            .siSo(UPDATED_SI_SO);
        return lop;
    }

    @BeforeEach
    public void initTest() {
        lop = createEntity(em);
    }

    @Test
    @Transactional
    public void createLop() throws Exception {
        int databaseSizeBeforeCreate = lopRepository.findAll().size();

        // Create the Lop
        restLopMockMvc.perform(post("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isCreated());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeCreate + 1);
        Lop testLop = lopList.get(lopList.size() - 1);
        assertThat(testLop.getMaLop()).isEqualTo(DEFAULT_MA_LOP);
        assertThat(testLop.getTenLop()).isEqualTo(DEFAULT_TEN_LOP);
        assertThat(testLop.getSiSo()).isEqualTo(DEFAULT_SI_SO);
    }

    @Test
    @Transactional
    public void createLopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lopRepository.findAll().size();

        // Create the Lop with an existing ID
        lop.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLopMockMvc.perform(post("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isBadRequest());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaLopIsRequired() throws Exception {
        int databaseSizeBeforeTest = lopRepository.findAll().size();
        // set the field null
        lop.setMaLop(null);

        // Create the Lop, which fails.

        restLopMockMvc.perform(post("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isBadRequest());

        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenLopIsRequired() throws Exception {
        int databaseSizeBeforeTest = lopRepository.findAll().size();
        // set the field null
        lop.setTenLop(null);

        // Create the Lop, which fails.

        restLopMockMvc.perform(post("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isBadRequest());

        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSiSoIsRequired() throws Exception {
        int databaseSizeBeforeTest = lopRepository.findAll().size();
        // set the field null
        lop.setSiSo(null);

        // Create the Lop, which fails.

        restLopMockMvc.perform(post("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isBadRequest());

        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLops() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        // Get all the lopList
        restLopMockMvc.perform(get("/api/lops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lop.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLop").value(hasItem(DEFAULT_MA_LOP)))
            .andExpect(jsonPath("$.[*].tenLop").value(hasItem(DEFAULT_TEN_LOP)))
            .andExpect(jsonPath("$.[*].siSo").value(hasItem(DEFAULT_SI_SO)));
    }
    
    @Test
    @Transactional
    public void getLop() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        // Get the lop
        restLopMockMvc.perform(get("/api/lops/{id}", lop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lop.getId().intValue()))
            .andExpect(jsonPath("$.maLop").value(DEFAULT_MA_LOP))
            .andExpect(jsonPath("$.tenLop").value(DEFAULT_TEN_LOP))
            .andExpect(jsonPath("$.siSo").value(DEFAULT_SI_SO));
    }

    @Test
    @Transactional
    public void getNonExistingLop() throws Exception {
        // Get the lop
        restLopMockMvc.perform(get("/api/lops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLop() throws Exception {
        // Initialize the database
        lopService.save(lop);

        int databaseSizeBeforeUpdate = lopRepository.findAll().size();

        // Update the lop
        Lop updatedLop = lopRepository.findById(lop.getId()).get();
        // Disconnect from session so that the updates on updatedLop are not directly saved in db
        em.detach(updatedLop);
        updatedLop
            .maLop(UPDATED_MA_LOP)
            .tenLop(UPDATED_TEN_LOP)
            .siSo(UPDATED_SI_SO);

        restLopMockMvc.perform(put("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLop)))
            .andExpect(status().isOk());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeUpdate);
        Lop testLop = lopList.get(lopList.size() - 1);
        assertThat(testLop.getMaLop()).isEqualTo(UPDATED_MA_LOP);
        assertThat(testLop.getTenLop()).isEqualTo(UPDATED_TEN_LOP);
        assertThat(testLop.getSiSo()).isEqualTo(UPDATED_SI_SO);
    }

    @Test
    @Transactional
    public void updateNonExistingLop() throws Exception {
        int databaseSizeBeforeUpdate = lopRepository.findAll().size();

        // Create the Lop

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLopMockMvc.perform(put("/api/lops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lop)))
            .andExpect(status().isBadRequest());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLop() throws Exception {
        // Initialize the database
        lopService.save(lop);

        int databaseSizeBeforeDelete = lopRepository.findAll().size();

        // Delete the lop
        restLopMockMvc.perform(delete("/api/lops/{id}", lop.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
