package com.med.hospital;

import com.med.hospital.entities.*;
import com.med.hospital.repositories.ConsultationRepository;
import com.med.hospital.repositories.MedecinRepository;
import com.med.hospital.repositories.PatientRepository;
import com.med.hospital.repositories.RendezVousRepository;
import com.med.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			IHospitalService iHospitalService,
			//maintenant tu n'a pas besoin de injecter les repository ici parce que il est deja injecte et appeler
			//dans la couche metier(service).
			PatientRepository patientRepository,
			MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository,
			ConsultationRepository consultationRepository){
		return args -> {
			Stream.of("Mohamed","Hassan","Najat")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						iHospitalService.savePatient(patient);
					});
			Stream.of("aymane","hanane","yasmine")
					.forEach(name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						iHospitalService.saveMedecin(medecin);
					});

			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Mohamed"); // si il y'a plusieur mohamed va genere une exception

			Medecin medecin=medecinRepository.findByNom("yasmine");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRDV=iHospitalService.saveRDV(rendezVous);
			System.out.println(savedRDV.getId());

			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation ....");
			iHospitalService.saveConsultation(consultation);
		};
	}
}
