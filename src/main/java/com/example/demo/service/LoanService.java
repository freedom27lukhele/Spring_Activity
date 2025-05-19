package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> findAll();

    Loan findById(int id);

    Loan save(Loan theLoan);

    void deleteById(int id);
}
