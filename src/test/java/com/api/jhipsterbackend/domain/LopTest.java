package com.api.jhipsterbackend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.jhipsterbackend.web.rest.TestUtil;

public class LopTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lop.class);
        Lop lop1 = new Lop();
        lop1.setId(1L);
        Lop lop2 = new Lop();
        lop2.setId(lop1.getId());
        assertThat(lop1).isEqualTo(lop2);
        lop2.setId(2L);
        assertThat(lop1).isNotEqualTo(lop2);
        lop1.setId(null);
        assertThat(lop1).isNotEqualTo(lop2);
    }
}
