package com.javacakes.application.data.repository;

import com.javacakes.application.data.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

}