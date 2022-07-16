package com.example.bank_app.repositories;


import com.example.bank_app.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    List<Account> findAccountByCustomerId(Long customerId);

}
