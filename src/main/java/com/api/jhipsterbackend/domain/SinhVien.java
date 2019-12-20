package com.api.jhipsterbackend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SinhVien.
 */
@Entity
@Table(name = "sinh_vien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SinhVien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ma_sv", nullable = false)
    private String maSV;

    @NotNull
    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "que_quan")
    private String queQuan;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @ManyToOne
    @JsonIgnoreProperties("sinhviens")
    private Lop lop;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public SinhVien maSV(String maSV) {
        this.maSV = maSV;
        return this;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public SinhVien hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public SinhVien gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public SinhVien queQuan(String queQuan) {
        this.queQuan = queQuan;
        return this;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public SinhVien soDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Lop getLop() {
        return lop;
    }

    public SinhVien lop(Lop lop) {
        this.lop = lop;
        return this;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SinhVien)) {
            return false;
        }
        return id != null && id.equals(((SinhVien) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
            "id=" + getId() +
            ", maSV='" + getMaSV() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", queQuan='" + getQueQuan() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            "}";
    }
}
