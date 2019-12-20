package com.api.jhipsterbackend.repository;
import com.api.jhipsterbackend.domain.SinhVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SinhVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {

}
