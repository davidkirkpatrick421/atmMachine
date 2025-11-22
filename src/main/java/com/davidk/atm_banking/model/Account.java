package com.davidk.atm_banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // SECURITY: Never return PIN in API JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pin;
    private BigDecimal balance;

    @Version // Optimistic Locking (Prevents double-spend race conditions)
    private Integer version;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore // Don't load full history when just checking balance
    private List<Transaction> transactions;

    public Account() {}

    public Account(Integer pin, BigDecimal balance) {
        this.pin = pin;
        this.balance = balance;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    @JsonIgnore // SECURITY: Never return PIN in API JSON
    public Integer getPin() { return pin; }
    public void setPin(Integer pin) { this.pin = pin; }

    public List<Transaction> getTransactions() { return transactions; }
}