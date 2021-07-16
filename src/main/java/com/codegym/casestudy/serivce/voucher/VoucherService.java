package com.codegym.casestudy.serivce.voucher;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.repository.IVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
@Transactional
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
        voucherRepository.deleteById(id);
    }

    @Override
    public void restoreVoucher(Long id) {
        voucherRepository.deleteVoucherById(id);
    }

    @Override
    public Iterable<Voucher> findAllByOrderByVoucher_idDesc() {
        try {
            checkApplying();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return voucherRepository.findAllByOrderByVoucher_idDesc();
    }

    @Override
    public Iterable<Voucher> findAllByOrderByVoucher_id() {
        try {
            checkUndue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return voucherRepository.findAllByOrderByVoucher_id();
    }

    @Override
    public Iterable<Voucher> findAllByOrderByVoucher_idExpired() {
        try {
            checkExpired();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return voucherRepository.findAllByOrderByVoucher_idExpired();
    }

    @Override
    public void restoreVoucherById(Long id) {
        voucherRepository.restoreVoucherById(id);
    }

    @Override
    public void checkApplying() throws ParseException {
        Iterable<Voucher> listVoucher = voucherRepository.findAllByOrderByVoucher_idDesc();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.util.Date currentDate = new java.sql.Date(utilDate.getTime());
        java.util.Date releaseDay;
        java.util.Date expDay;
        for (Voucher v: listVoucher
        ) {
            releaseDay = formatter.parse(v.getRelease_date());
            expDay = formatter.parse(v.getExpiration_date());
            if (currentDate.getTime()-releaseDay.getTime()>0 && expDay.getTime()-currentDate.getTime()>0){
                v.setExpired(false);
                voucherRepository.save(v);
            }else{
                v.setExpired(true);
                voucherRepository.save(v);
            }
        }
    }

    @Override
    public void checkUndue() throws ParseException {
        Iterable<Voucher> listVoucher = voucherRepository.findAllByVoucherIsUndue();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.util.Date currentDate = new java.sql.Date(utilDate.getTime());
        java.util.Date releaseDay;
        java.util.Date expDay;
        for (Voucher v: listVoucher
        ) {
            releaseDay = formatter.parse(v.getRelease_date());
            expDay = formatter.parse(v.getExpiration_date());
            if (currentDate.getTime()-releaseDay.getTime()<0){
                v.setUndue(true);
                voucherRepository.save(v);
            }else{
                v.setUndue(false);
                voucherRepository.save(v);
            }
        }
    }

    @Override
    public void checkExpired() throws ParseException {
        Iterable<Voucher> listVoucher = voucherRepository.findAllByVoucherIsUndue();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new java.util.Date();
        java.util.Date currentDate = new java.sql.Date(utilDate.getTime());
        java.util.Date releaseDay;
        java.util.Date expDay;
        for (Voucher v: listVoucher
        ) {
            releaseDay = formatter.parse(v.getRelease_date());
            expDay = formatter.parse(v.getExpiration_date());
            if (expDay.getTime() - currentDate.getTime()<0){
                v.setUndue(false);
                voucherRepository.save(v);
            }else{
                v.setUndue(true);
                voucherRepository.save(v);
            }
        }
    }
}