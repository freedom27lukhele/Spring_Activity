package com.example.demo.service.impl;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.LoanRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private ClientRepository clientRepository;
    private LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(int theId) {
        Optional<Loan> loan = loanRepository.findById(theId);

        Loan theLoan = null;
        if (loan.isPresent()) {
            theLoan = loan.get();
        } else {
            throw new RuntimeException("Did not find loan with id " + theId);
        }
        return theLoan;
    }

    @Override
    public Loan save(Loan theLoan) {
     return loanRepository.save(theLoan);
    }

    @Override
    public void deleteById(int theId) {
        loanRepository.deleteById(theId);
    }
}
