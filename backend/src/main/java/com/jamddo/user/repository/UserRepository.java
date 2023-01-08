package com.jamddo.user.repository;


import com.jamddo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom{
    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);
}
