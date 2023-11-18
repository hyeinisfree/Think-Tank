package com.ttteam.thinktank.repository;

import com.ttteam.thinktank.entity.Account;
import com.ttteam.thinktank.entity.Star;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
    List<Star> findAllByAccountAndCreatedAtBetween(Account account, LocalDateTime firstDate, LocalDateTime lastDate);
}
