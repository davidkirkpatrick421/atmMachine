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

    // 1. Create Account (Helper for you/Recruiters to set up data)
    @PostMapping("/accounts")
    public Account create(@RequestBody Account account) {
        return repo.save(account);
    }

    // 2. Withdraw Money
    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam Long id, @RequestParam Integer pin, @RequestParam BigDecimal amount) {
        return service.withdraw(id, pin, amount);
    }

    // 3. Check Balance (Debug)
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}