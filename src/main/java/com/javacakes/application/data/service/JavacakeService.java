package com.javacakes.application.data.service;

import com.javacakes.application.data.entity.Adherence;
import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Patient;
import com.javacakes.application.data.entity.Pillbox;
import com.javacakes.application.data.repository.AdherenceRepository;
import com.javacakes.application.data.repository.MedicationRepository;
import com.javacakes.application.data.repository.PatientRepository;
import com.javacakes.application.data.repository.PillboxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavacakeService {

    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;
    private final PillboxRepository pillboxRepository;
    private final AdherenceRepository adherenceRepository;

    public JavacakeService(PatientRepository patientRepository,
                           MedicationRepository medicationRepository,
                           PillboxRepository pillboxRepository,
                           AdherenceRepository adherenceRepository) {
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
        this.pillboxRepository = pillboxRepository;
        this.adherenceRepository = adherenceRepository;
    }

    public List<Medication> findAllMedication() {
        return medicationRepository.findAll();
    }

    public long countMedication() {
        return medicationRepository.count();
    }

    public void deleteMedication(Medication medication) {
        medicationRepository.delete(medication);
    }

    public void saveMedication(Medication medication) {
        if (medication == null) {
            System.err.println("Medication is null.");
            return;
        }
        medicationRepository.save(medication);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    public List<Pillbox> findAllPillbox(){
        return pillboxRepository.findAll();
    }

    public long countPillbox() {
        return pillboxRepository.count();
    }

    public List<Adherence> findAllAdherence(){
        return adherenceRepository.findAll();
    }

    public long countAdherence() {
        return adherenceRepository.count();
    }
}