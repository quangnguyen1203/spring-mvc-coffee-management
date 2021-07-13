package com.codegym.casestudy.serivce.voucher;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.serivce.IGeneralService;

import java.text.ParseException;

public interface IVoucherService extends IGeneralService<Voucher> {
    Iterable<Voucher> findAllByOrderByVoucher_idDesc();

    Iterable<Voucher> findAllVoucher_idDesc();

    void restoreVoucherById(Long id);

    void checkExpired() throws ParseException;
}
