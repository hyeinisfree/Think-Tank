package com.ttteam.thinktank.repository;

import com.ttteam.thinktank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
