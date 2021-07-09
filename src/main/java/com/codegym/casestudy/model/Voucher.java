package com.codegym.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucher_id;
    private String voucher_name;
    private double percent_discount;
    private Date release_date;
    private Date expiration_date;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean isDelete;

    public Voucher() {
    }

    public Voucher(String voucher_name, double percent_discount, Date release_date, Date expiration_date) {
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
    }

    public Voucher(Long voucher_id, String voucher_name, double percent_discount, Date release_date, Date expiration_date) {
        this.voucher_id = voucher_id;
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
    }

    public Voucher(String voucher_name, double percent_discount, Date release_date, Date expiration_date, boolean isDelete) {
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isDelete = isDelete;
    }

    public Voucher(Long voucher_id, String voucher_name, double percent_discount, Date release_date, Date expiration_date, boolean isDelete) {
        this.voucher_id = voucher_id;
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isDelete = isDelete;
    }

    public Long getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(Long voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public double getPercent_discount() {
        return percent_discount;
    }

    public void setPercent_discount(double percent_discount) {
        this.percent_discount = percent_discount;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
