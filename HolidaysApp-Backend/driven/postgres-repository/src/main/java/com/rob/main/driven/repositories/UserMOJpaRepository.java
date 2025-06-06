package com.rob.main.driven.repositories;

import com.rob.main.driven.repositories.models.UserMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserMOJpaRepository extends JpaRepository<UserMO, Integer> {
    List<UserMO> findByUsernameContaining(String username);
    @NonNull List<UserMO> findAll();

    UserMO findByUsername(String username);

    UserMO findByEmail(String email);

    @NonNull Optional<UserMO> findById(@NonNull Integer id);

    Optional<UserMO> findByUsernameOrEmail(String username, String email);

    boolean existsByCodeColor(String codeColor);

    @Query("SELECT u FROM UserMO u WHERE LOWER(CONCAT(u.employee.firstName, ' ', u.employee.lastName)) LIKE LOWER(CONCAT('%', :nameAndLastName, '%'))")
    List<UserMO> findByFullName(@Param("nameAndLastName") String nameAndLastName);

}