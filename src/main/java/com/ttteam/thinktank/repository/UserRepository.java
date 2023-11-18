package com.ttteam.thinktank.repository;

import com.ttteam.thinktank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

}
