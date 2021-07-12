package com.codegym.casestudy.serivce.voucher;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.repository.IVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private IVoucherRepository voucherRepository;

    @Override
    public Iterable<Voucher> findAll() {
        return voucherRepository.findAllByOrderByVoucher_idDesc();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public void remove(Long id) {
        voucherRepository.deleteVoucherById(id);
    }

    @Override
    public Iterable<Voucher> findAllByOrderByVoucher_idDesc() {
//        checkExpired();
        return voucherRepository.findAllByOrderByVoucher_idDesc();
    }

    @Override
    public Iterable<Voucher> findAllVoucher_idDesc() {
        return voucherRepository.findAllVoucher_idDesc();
    }

    @Override
    public void restoreVoucherById(Long id) {
        voucherRepository.restoreVoucherById(id);
    }

//    @Override
//    public void checkExpired() {
//        Iterable<Voucher> listVoucher = voucherRepository.findAllByOrderByVoucher_idDesc();
//        for (Voucher v: listVoucher
//             ) {
//            if (compareDate(v.getExpiration_date())<=0){
//                v.setExpired(true);
//                voucherRepository.save(v);
//            }
//        }
//    }
//
//   private int compareDate(Date expDate) {
//       java.util.Date utilDate = new java.util.Date();
//       java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
//       return expDate.compareTo(currentDate);
//    }
}
