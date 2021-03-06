package com.codegym.casestudy.repository;
import com.codegym.casestudy.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface IVoucherRepository extends JpaRepository<Voucher, Long> {
    @Query("select v from Voucher v where (v.isExpired = false and v.isUndue = false) order by v.voucher_id desc")
    Iterable<Voucher> findAllByOrderByVoucher_idDesc();

    @Query("select v from Voucher v where (v.isExpired = true and v.isUndue = true ) order by v.voucher_id desc")
    Iterable<Voucher> findAllByOrderByVoucher_id();

    @Query("select v from Voucher v where (v.isExpired = true and v.isUndue = false ) order by v.voucher_id desc")
    Iterable<Voucher> findAllByOrderByVoucher_idExpired();

    @Modifying
    @Query("update Voucher v set v.isExpired = true where v.voucher_id = :id")
    void deleteVoucherById(@Param("id") Long id);

    @Query("select v from Voucher v where v.isExpired = true order by v.voucher_id desc")
    Iterable<Voucher> findAllVoucherIsExpired();

    @Modifying
    @Query("update Voucher v set v.isExpired = false where v.voucher_id = :id")
    void restoreVoucherById(@Param("id") Long id);

    @Modifying
    @Query("select v from Voucher v where v.isExpired = true order by v.voucher_id desc")
    Iterable<Voucher> findAllByVoucherIsUndue();

    @Query("select v from Voucher v where (v.isExpired = true and v.isUndue = false) order by v.voucher_id desc")
    Iterable<Voucher> findAllByVoucherExpired();
}