package ru.startup.verifier_service.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.startup.verifier_service.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.uuid = :uuid")
    void updateUserRole(@Param("uuid") UUID uuid, @Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.uuid = :uuid")
    Optional<User> findByUUID(@Param("uuid") UUID uuid);

}
