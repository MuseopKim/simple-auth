package com.simpleauth.repository;

import com.simpleauth.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("ID, Password를 값으로 Account를 조회한다.")
    @Test
    void findByIdAndPassword() {
        // given
        Account testAccount = Account.builder()
                                .id("UserID")
                                .password("UserPassword")
                                .build();

        Account account = accountRepository.save(testAccount);

        // when
        Account foundAccount = accountRepository.findBy(account.getId(), account.getPassword()).orElseThrow(RuntimeException::new);

        // then
        assertThat(account).usingRecursiveComparison().isEqualTo(foundAccount);
    }
}
