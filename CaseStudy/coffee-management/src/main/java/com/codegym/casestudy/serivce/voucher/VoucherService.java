package com.codegym.casestudy.serivce.voucher;

import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.repository.IVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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

    }
}
