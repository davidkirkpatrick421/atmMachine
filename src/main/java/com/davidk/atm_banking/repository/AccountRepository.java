package com.davidk.atm_banking.repository;

import com.davidk.atm_banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}