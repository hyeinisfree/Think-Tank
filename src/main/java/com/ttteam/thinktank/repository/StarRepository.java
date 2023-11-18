package com.ttteam.thinktank.repository;

import com.ttteam.thinktank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Account, Long> {

}
