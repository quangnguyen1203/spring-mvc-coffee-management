package com.codegym.casestudy.model;

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
    private Date release_date;
    private Date expiration_date;

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


}
