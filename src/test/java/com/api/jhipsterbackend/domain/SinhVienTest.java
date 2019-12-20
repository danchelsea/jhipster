package com.api.jhipsterbackend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.jhipsterbackend.web.rest.TestUtil;

public class SinhVienTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SinhVien.class);
        SinhVien sinhVien1 = new SinhVien();
        sinhVien1.setId(1L);
        SinhVien sinhVien2 = new SinhVien();
        sinhVien2.setId(sinhVien1.getId());
        assertThat(sinhVien1).isEqualTo(sinhVien2);
        sinhVien2.setId(2L);
        assertThat(sinhVien1).isNotEqualTo(sinhVien2);
        sinhVien1.setId(null);
        assertThat(sinhVien1).isNotEqualTo(sinhVien2);
    }
}
