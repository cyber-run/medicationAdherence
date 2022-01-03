package com.javacakes.application.data.repository;

import com.javacakes.application.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}