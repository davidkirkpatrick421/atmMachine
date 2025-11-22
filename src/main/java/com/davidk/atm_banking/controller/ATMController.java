package com.davidk.atm_banking.controller;

import com.davidk.atm_banking.model.Account;
import com.davidk.atm_banking.service.BankingService;
import com.davidk.atm_banking.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    private final BankingService service;
    private final AccountRepository repo;

    public ATMController(BankingService service, AccountRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    // 1. Create Account (For setup)
    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return repo.save(account);
    }

    // 2. Withdraw
    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam Long id, @RequestParam Integer pin, @RequestParam BigDecimal amount) {
        return service.withdraw(id, pin, amount);
    }

    // 3. Deposit
    @PostMapping("/deposit")
    public Account deposit(@RequestParam Long id, @RequestParam BigDecimal amount) {
        return service.deposit(id, amount);
    }

    // 4. Balance Check
    // This returns the full JSON: { "id": 1, "balance": 50.00, ... }
    @GetMapping("/{id}")
    public Account getAccountBalance(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
    }
}