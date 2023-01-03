package com.jamddo.user.repository;


import com.jamddo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByNickname(String nickname);
}
