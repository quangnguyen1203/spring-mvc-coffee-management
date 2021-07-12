package com.codegym.casestudy.serivce.voucher;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.serivce.IGeneralService;

public interface IVoucherService extends IGeneralService<Voucher> {
    Iterable<Voucher> findAllByOrderByVoucher_idDesc();

    Iterable<Voucher> findAllVoucher_idDesc();

    void restoreVoucherById(Long id);

//    void checkExpired();
}
