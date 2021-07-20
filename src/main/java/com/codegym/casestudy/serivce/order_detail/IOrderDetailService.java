package com.codegym.casestudy.serivce.order_detail;

import com.codegym.casestudy.model.OrderDetail;
import com.codegym.casestudy.serivce.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findByOrder_id(Long id);
}
