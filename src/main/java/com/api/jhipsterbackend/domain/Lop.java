package com.api.jhipsterbackend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Lop.
 */
@Entity
@Table(name = "lop")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Lop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ma_lop", nullable = false)
    private String maLop;

    @NotNull
    @Column(name = "ten_lop", nullable = false)
    private String tenLop;

    @NotNull
    @Column(name = "si_so", nullable = false)
    private Integer siSo;

    @OneToMany(mappedBy = "lop")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SinhVien> sinhviens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public Lop maLop(String maLop) {
        this.maLop = maLop;
        return this;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public Lop tenLop(String tenLop) {
        this.tenLop = tenLop;
        return this;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Integer getSiSo() {
        return siSo;
    }

    public Lop siSo(Integer siSo) {
        this.siSo = siSo;
        return this;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    public Set<SinhVien> getSinhviens() {
        return sinhviens;
    }

    public Lop sinhviens(Set<SinhVien> sinhViens) {
        this.sinhviens = sinhViens;
        return this;
    }

    public Lop addSinhvien(SinhVien sinhVien) {
        this.sinhviens.add(sinhVien);
        sinhVien.setLop(this);
        return this;
    }

    public Lop removeSinhvien(SinhVien sinhVien) {
        this.sinhviens.remove(sinhVien);
        sinhVien.setLop(null);
        return this;
    }

    public void setSinhviens(Set<SinhVien> sinhViens) {
        this.sinhviens = sinhViens;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lop)) {
            return false;
        }
        return id != null && id.equals(((Lop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lop{" +
            "id=" + getId() +
            ", maLop='" + getMaLop() + "'" +
            ", tenLop='" + getTenLop() + "'" +
            ", siSo=" + getSiSo() +
            "}";
    }
}
