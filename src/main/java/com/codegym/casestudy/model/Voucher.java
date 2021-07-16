package com.codegym.casestudy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucher_id;
    private String voucher_name;
    private double percent_discount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String release_date;

    private String expiration_date;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean isExpired;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean isUndue;

    public Voucher() {
    }

    public Voucher(String voucher_name, double percent_discount, String release_date, String expiration_date) {
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
    }

    public Voucher(Long voucher_id, String voucher_name, double percent_discount, String release_date, String expiration_date) {
        this.voucher_id = voucher_id;
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
    }

    public Voucher(String voucher_name, double percent_discount, String release_date, String expiration_date, boolean isExpired) {
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isExpired = isExpired;
    }

    public Voucher(Long voucher_id, String voucher_name, double percent_discount, String release_date, String expiration_date, boolean isExpired) {
        this.voucher_id = voucher_id;
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isExpired = isExpired;
    }

    public Voucher(String voucher_name, double percent_discount, String release_date, String expiration_date, boolean isExpired, boolean isUndue) {
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isExpired = isExpired;
        this.isUndue = isUndue;
    }

    public Voucher(Long voucher_id, String voucher_name, double percent_discount, String release_date, String expiration_date, boolean isExpired, boolean isUndue) {
        this.voucher_id = voucher_id;
        this.voucher_name = voucher_name;
        this.percent_discount = percent_discount;
        this.release_date = release_date;
        this.expiration_date = expiration_date;
        this.isExpired = isExpired;
        this.isUndue = isUndue;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isUndue() {
        return isUndue;
    }

    public void setUndue(boolean undue) {
        isUndue = undue;
    }
}
