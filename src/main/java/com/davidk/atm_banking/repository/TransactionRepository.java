package com.davidk.atm_banking.repository;

import com.davidk.atm_banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}