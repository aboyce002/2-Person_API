package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Bill;
import com.aboyce002.Person_API.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    public List<Bill> getAllBillsByAcc(Long accountId) {
        List<Bill> listOfBills = new ArrayList<>();
        billRepository.findAll().forEach(listOfBills::add);
        return listOfBills;
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public List<Bill> getAllBillsForCus(Long customerId) {
        List<Bill> listOfBillsForCus = new ArrayList<>();
        billRepository.findAll().forEach(listOfBillsForCus::add);
        return listOfBillsForCus;
    }

    public void createBill(Bill bill) {
        billRepository.save(bill);
    }

    public Bill updateBill(Long id, Bill bill) {
        bill.setId(id);
        return billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return billRepository.existsById(id);
    }

    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    public Optional<Bill> findAllByPayeeId(Long id) {
        return billRepository.findById(id);
    }
}
