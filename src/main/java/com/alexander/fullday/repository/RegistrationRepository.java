package com.alexander.fullday.repository;

import com.alexander.fullday.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    Optional<Registration> findByDocumentNumber(String documentNumber);
    Optional<Registration> findByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
