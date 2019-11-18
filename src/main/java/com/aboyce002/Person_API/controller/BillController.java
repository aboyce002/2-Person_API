package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Bill;
import com.aboyce002.Person_API.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/accoutns/{accountId}/bills")
    public List<Bill> getAllBillsForAcc(@PathVariable("accountId") Long id, @RequestBody Bill bill) {
        return billService.getAllBillsByAcc(id);
    }

    @GetMapping("/bills/{billId}")
    public Optional<Bill> getBillById(@PathVariable("billId") Long id) {
        return billService.getBillById(id);
    }

    @GetMapping("/custoemrs/{customerId}/bills")
    public List<Bill>  getAllBillsForCus(@PathVariable("customerId") Long id, @RequestBody Bill bill) {
        return billService.getAllBillsForCus(id);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public void createBill(@RequestBody Bill bill, @PathVariable("accountId") Long id) {
        billService.createBill(bill);
    }

    @PutMapping("/bills/{billId}")
    public void updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        billService.updateBill(id, bill);
    }

    @DeleteMapping("/bills/{billId}")
    public void deleteBill(@PathVariable("billId") Long id) {
        billService.deleteBill(id);
    }


}
