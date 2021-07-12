package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Category;
import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.serivce.voucher.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ModelAndView getAllVoucherPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/list");
        modelAndView.addObject("vouchers",voucherService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-voucher")
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


    @GetMapping("/hiddenVoucher")
    public ModelAndView getAllVoucherHiddenPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/voucher/hiddenVoucher");
        modelAndView.addObject("hiddenVouchers",voucherService.findAllVoucher_idDesc());
        return modelAndView;
    }
}
