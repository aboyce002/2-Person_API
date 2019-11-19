package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Bill;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsForAcc(@PathVariable("accountId") Long id, @RequestBody Bill bill) {
        ResponseStateReturn rep = new ResponseStateReturn();
        if(!billService.existsById(id)) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("error fetching bills");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            Optional<Bill> billServiceAllByPayeeId = billService.findAllByPayeeId(id);
            rep.setCode(HttpStatus.OK.value());
            rep.setData(Collections.singletonList(billServiceAllByPayeeId));
            rep.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        if(!billService.existsById(id)) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("error fetching bill with id " + id);
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            Optional<Bill> bill = billService.findById(id);
            rep.setCode(HttpStatus.OK.value());
            rep.setData(new ArrayList<>(Collections.singleton(bill)));
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsForCus(@PathVariable Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        if(!billService.existsById(id)) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("error fetching bills");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            Optional<Bill> bill1 = billService.findById(id);
            rep.setCode(HttpStatus.OK.value());
            rep.setData(new ArrayList<>(Collections.singleton(bill1)));
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable("accountId") Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Bill b = billService.createBill(bill, id);
        if(b == null) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error creating bill: Account not found");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            rep.setCode(HttpStatus.CREATED.value());
            rep.setMessage("Created bill and added it to the account");
            rep.setData(new ArrayList<>(Collections.singleton(bill)));
            return new ResponseEntity<>(rep, HttpStatus.CREATED);
        }
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        ResponseStateReturn rep = new ResponseStateReturn();
        if(!billService.existsById(id)) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Bill ID does not exist");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            billService.updateBill(id, bill);
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Accepted deposit modification");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        if(billService.existsById(id)) {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("This id does not exist in bills");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }else{
            billService.deleteBill(id);
            return new ResponseEntity<>(rep, HttpStatus.NO_CONTENT);
        }
    }


}
