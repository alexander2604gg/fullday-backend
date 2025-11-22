package com.alexander.fullday.repository;

import com.alexander.fullday.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByRegistrationId(Integer registrationId);
    boolean existsByRegistrationId(Integer registrationId);
    long countByRegistrationId(Integer registrationId);
}