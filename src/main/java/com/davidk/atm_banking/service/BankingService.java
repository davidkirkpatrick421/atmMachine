package com.davidk.atm_banking.service;

import com.davidk.atm_banking.model.*;
import com.davidk.atm_banking.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class BankingService {

    private final AccountRepository accountRepo;
    private final TransactionRepository transRepo;

    public BankingService(AccountRepository ar, TransactionRepository tr) {
        this.accountRepo = ar; this.transRepo = tr;
    }

    // --- WITHDRAW ---
    @Transactional
    public Account withdraw(Long accountId, Integer pin, BigDecimal amount) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!account.getPin().equals(pin)) throw new RuntimeException("Invalid PIN");
        if (account.getBalance().compareTo(amount) < 0) throw new RuntimeException("Insufficient Funds");

        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);
        transRepo.save(new Transaction(amount, "WITHDRAW", account));

        return account;
    }

    // --- DEPOSIT  ---
    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit must be positive");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepo.save(account);
        transRepo.save(new Transaction(amount, "DEPOSIT", account));

        return account;
    }
}