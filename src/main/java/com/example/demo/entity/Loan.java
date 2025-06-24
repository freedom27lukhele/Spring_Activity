package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "interest")
    private Double interest;
    @Column(name = "term_Months")
    private int termMonths;
    @CreationTimestamp
    @Column(name = "start_date", updatable = false)
    private Date startDate;
    @Column(name = "status")
    private String status;
    @Column(name = "total")
    private Double total;


     @ManyToOne
     @JoinColumn(name = "client_id",nullable = true)
     private Client client;

    public Loan(Client client,int id, Double amount, Double interest, int termMonths, Date startDate, String status,Double total) {
        this.id = id;
        this.amount = amount;
        this.interest = interest;
        this.termMonths = termMonths;
        this.startDate = startDate;
        this.status = status;
        this.client = client;
        this.total = total;
    }

    public Loan() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(int clientId) {
//        this.clientId = clientId;
//    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
