package com.med.hospital.service;

import com.med.hospital.entities.Consultation;
import com.med.hospital.entities.Medecin;
import com.med.hospital.entities.Patient;
import com.med.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
