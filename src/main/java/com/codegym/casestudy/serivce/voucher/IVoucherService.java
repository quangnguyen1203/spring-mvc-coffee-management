package com.codegym.casestudy.serivce.voucher;
import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.serivce.IGeneralService;
import java.text.ParseException;
public interface IVoucherService extends IGeneralService<Voucher> {

    Iterable<Voucher> findAllByOrderByVoucher_idDesc();

    Iterable<Voucher> findAllByOrderByVoucher_id();

    Iterable<Voucher> findAllByOrderByVoucher_idExpired();

    void restoreVoucherById(Long id);

    void checkApplying() throws ParseException;

    void checkUndue() throws ParseException;

    void checkExpired() throws ParseException;

    void restoreVoucher(Long id);
}