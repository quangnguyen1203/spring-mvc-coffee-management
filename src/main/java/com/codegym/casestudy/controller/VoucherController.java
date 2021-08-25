package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.serivce.voucher.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private IVoucherService voucherService;

    @GetMapping
    public ModelAndView homePage() {
        //        modelAndView.addObject("products",productService.findAll());
        return new ModelAndView("/dashboard/home");
    }

    @GetMapping("/listVoucher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView getAllVoucherPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/list");
        modelAndView.addObject("vouchers",voucherService.findAllByOrderByVoucher_idDesc());
        return modelAndView;
    }

    @GetMapping("/create-voucher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/create");
        modelAndView.addObject("voucher", new Voucher());
        return modelAndView;
    }

    @PostMapping("/create-voucher")
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher){
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Voucher> editVoucher(@RequestBody Voucher voucher, @PathVariable Long id){
        voucher.setVoucher_id(id);
        return new ResponseEntity<>(voucherService.save(voucher),HttpStatus.OK);
    }

    @GetMapping("/edit-voucher/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Voucher> voucherResponseEntity(@PathVariable Long id){
        Voucher voucherOptional = voucherService.findById(id).get();
        return new ResponseEntity<>(voucherOptional,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Voucher> deleteVoucher(@PathVariable Long id) {
        Optional <Voucher> voucherOptional = voucherService.findById(id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        voucherService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Voucher> restoreVoucher(@PathVariable Long id) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        voucherService.restoreVoucherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/listExpiredVoucher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView getAllVoucherHiddenPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/listExpiredVoucher");
        modelAndView.addObject("expiredVouchers",voucherService.findAllByOrderByVoucher_idExpired());
        return modelAndView;
    }

    @GetMapping("/listUndueVoucher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView getAllUndueVoucher(){
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/listUndueVoucher");
        modelAndView.addObject("undueVouchers",voucherService.findAllByOrderByVoucher_id());
        return modelAndView;
    }

    @GetMapping("/findVoucher/{id}")
    public ResponseEntity<Voucher> findById(@PathVariable Long id){
       Voucher voucher = voucherService.findById(id).get();
       return new ResponseEntity<>(voucher, HttpStatus.OK);
    }
}
