package com.ttteam.thinktank.repository;

import com.ttteam.thinktank.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUuid(String uuid);
    boolean existsByEmail(String email);
}
