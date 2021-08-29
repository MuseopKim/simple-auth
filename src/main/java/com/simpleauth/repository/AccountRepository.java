package com.simpleauth.repository;

import com.simpleauth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT a FROM Account a WHERE a.id = :id AND a.password = :password")
    Optional<Account> findBy(String id, String password);
}
