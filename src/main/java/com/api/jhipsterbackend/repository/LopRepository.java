package com.api.jhipsterbackend.repository;
import com.api.jhipsterbackend.domain.Lop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Lop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LopRepository extends JpaRepository<Lop, Long> {

}
