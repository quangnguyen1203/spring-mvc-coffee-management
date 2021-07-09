package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherRepository extends JpaRepository<Voucher, Long> {
    @Query("select c from Voucher c where c.isDelete = FALSE order by c.voucher_id desc")
    Iterable<Voucher> findAllByOrderByVoucher_idDesc();

    @Transactional
    @Modifying
    @Query("update Voucher c set c.isDelete = true where c.voucher_id = :id")
    void deleteVoucherById(@Param("id") Long id);
}
