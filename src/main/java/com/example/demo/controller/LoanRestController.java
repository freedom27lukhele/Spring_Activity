package com.example.demo.controller;


import com.example.demo.dao.LoanRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.Loan;
import com.example.demo.service.ClientService;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanRestController {

    private LoanService loanService;
    private ClientService clientService;

    @Autowired
    public LoanRestController(LoanService loanRepository,ClientService clientService) {
        this.loanService = loanRepository;
        this.clientService = clientService;
    }

    //get all available loans
    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/loans/{loanId}")
    public Loan getLoan(@PathVariable int loanId) {
        Loan theLoan = loanService.findById(loanId);

        if (theLoan == null) {
            throw new RuntimeException("Loan with id " + loanId + " not found");
        }

        return theLoan;

    }

    //loan in which one user can have multiple loans
    //this is to create a response body
    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan theLoan) {

        Client client = clientService.findById(theLoan.getClient().getId());
        if (client == null) {
            return ResponseEntity.badRequest().body(null);
        }
        // create an object for every loan created
        Loan loan = new Loan();
        loan.setAmount(theLoan.getAmount());
        loan.setInterest(theLoan.getInterest());
        loan.setTermMonths(theLoan.getTermMonths());
        loan.setStartDate(theLoan.getStartDate());
        loan.setStatus(theLoan.getStatus());
        loan.setClient(client);
        loan.setTotal(calculateTotal(theLoan.getAmount(),theLoan.getInterest(),theLoan.getTermMonths()));

        return ResponseEntity.ok(loanService.save(loan));
    }

    @PutMapping("/loans")
    public Loan updateLoan(@RequestBody Loan theLoan) {

        return loanService.save(theLoan);
    }

    @DeleteMapping("/loans/{loanid}")
    public void deleteLoan(@PathVariable int loanId) {
        loanService.deleteById(loanId);
    }


    //this is to create the total of the loan
    public static double calculateTotal(double amount,double interest,int termMonths) {
       return Math.round(amount * Math.pow(1+(interest/100),termMonths));
    }

}
