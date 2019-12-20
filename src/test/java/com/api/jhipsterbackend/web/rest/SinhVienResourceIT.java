package com.api.jhipsterbackend.web.rest;

import com.api.jhipsterbackend.BackendApp;
import com.api.jhipsterbackend.domain.SinhVien;
import com.api.jhipsterbackend.repository.SinhVienRepository;
import com.api.jhipsterbackend.service.SinhVienService;
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
 * Integration tests for the {@link SinhVienResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
public class SinhVienResourceIT {

    private static final String DEFAULT_MA_SV = "AAAAAAAAAA";
    private static final String UPDATED_MA_SV = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_QUE_QUAN = "AAAAAAAAAA";
    private static final String UPDATED_QUE_QUAN = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private SinhVienService sinhVienService;

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

    private MockMvc restSinhVienMockMvc;

    private SinhVien sinhVien;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SinhVienResource sinhVienResource = new SinhVienResource(sinhVienService);
        this.restSinhVienMockMvc = MockMvcBuilders.standaloneSetup(sinhVienResource)
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
    public static SinhVien createEntity(EntityManager em) {
        SinhVien sinhVien = new SinhVien()
            .maSV(DEFAULT_MA_SV)
            .hoTen(DEFAULT_HO_TEN)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .queQuan(DEFAULT_QUE_QUAN)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI);
        return sinhVien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SinhVien createUpdatedEntity(EntityManager em) {
        SinhVien sinhVien = new SinhVien()
            .maSV(UPDATED_MA_SV)
            .hoTen(UPDATED_HO_TEN)
            .gioiTinh(UPDATED_GIOI_TINH)
            .queQuan(UPDATED_QUE_QUAN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI);
        return sinhVien;
    }

    @BeforeEach
    public void initTest() {
        sinhVien = createEntity(em);
    }

    @Test
    @Transactional
    public void createSinhVien() throws Exception {
        int databaseSizeBeforeCreate = sinhVienRepository.findAll().size();

        // Create the SinhVien
        restSinhVienMockMvc.perform(post("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isCreated());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeCreate + 1);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSV()).isEqualTo(DEFAULT_MA_SV);
        assertThat(testSinhVien.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testSinhVien.getQueQuan()).isEqualTo(DEFAULT_QUE_QUAN);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(DEFAULT_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    public void createSinhVienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sinhVienRepository.findAll().size();

        // Create the SinhVien with an existing ID
        sinhVien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSinhVienMockMvc.perform(post("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaSVIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setMaSV(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc.perform(post("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = sinhVienRepository.findAll().size();
        // set the field null
        sinhVien.setHoTen(null);

        // Create the SinhVien, which fails.

        restSinhVienMockMvc.perform(post("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSinhViens() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get all the sinhVienList
        restSinhVienMockMvc.perform(get("/api/sinh-viens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sinhVien.getId().intValue())))
            .andExpect(jsonPath("$.[*].maSV").value(hasItem(DEFAULT_MA_SV)))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].queQuan").value(hasItem(DEFAULT_QUE_QUAN)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)));
    }
    
    @Test
    @Transactional
    public void getSinhVien() throws Exception {
        // Initialize the database
        sinhVienRepository.saveAndFlush(sinhVien);

        // Get the sinhVien
        restSinhVienMockMvc.perform(get("/api/sinh-viens/{id}", sinhVien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sinhVien.getId().intValue()))
            .andExpect(jsonPath("$.maSV").value(DEFAULT_MA_SV))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH))
            .andExpect(jsonPath("$.queQuan").value(DEFAULT_QUE_QUAN))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI));
    }

    @Test
    @Transactional
    public void getNonExistingSinhVien() throws Exception {
        // Get the sinhVien
        restSinhVienMockMvc.perform(get("/api/sinh-viens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSinhVien() throws Exception {
        // Initialize the database
        sinhVienService.save(sinhVien);

        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();

        // Update the sinhVien
        SinhVien updatedSinhVien = sinhVienRepository.findById(sinhVien.getId()).get();
        // Disconnect from session so that the updates on updatedSinhVien are not directly saved in db
        em.detach(updatedSinhVien);
        updatedSinhVien
            .maSV(UPDATED_MA_SV)
            .hoTen(UPDATED_HO_TEN)
            .gioiTinh(UPDATED_GIOI_TINH)
            .queQuan(UPDATED_QUE_QUAN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI);

        restSinhVienMockMvc.perform(put("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSinhVien)))
            .andExpect(status().isOk());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
        SinhVien testSinhVien = sinhVienList.get(sinhVienList.size() - 1);
        assertThat(testSinhVien.getMaSV()).isEqualTo(UPDATED_MA_SV);
        assertThat(testSinhVien.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testSinhVien.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testSinhVien.getQueQuan()).isEqualTo(UPDATED_QUE_QUAN);
        assertThat(testSinhVien.getSoDienThoai()).isEqualTo(UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    public void updateNonExistingSinhVien() throws Exception {
        int databaseSizeBeforeUpdate = sinhVienRepository.findAll().size();

        // Create the SinhVien

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSinhVienMockMvc.perform(put("/api/sinh-viens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sinhVien)))
            .andExpect(status().isBadRequest());

        // Validate the SinhVien in the database
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSinhVien() throws Exception {
        // Initialize the database
        sinhVienService.save(sinhVien);

        int databaseSizeBeforeDelete = sinhVienRepository.findAll().size();

        // Delete the sinhVien
        restSinhVienMockMvc.perform(delete("/api/sinh-viens/{id}", sinhVien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SinhVien> sinhVienList = sinhVienRepository.findAll();
        assertThat(sinhVienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
