package com.davidk.atm_banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String type; // "DEPOSIT" or "WITHDRAW"
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore // vital: stops infinite loops (Account -> Transaction -> Account...)
    private Account account;

    public Transaction() {}

    public Transaction(BigDecimal amount, String type, Account account) {
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
}