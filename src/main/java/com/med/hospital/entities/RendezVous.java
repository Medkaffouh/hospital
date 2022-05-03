package com.med.hospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    @Id
    private String id; //esseyer d'utiliser maintenant le id comme string // voir ce class dans l'implemetation metier
    //@Temporal(TemporalType.DATE)
    private Date date;
    @Enumerated(EnumType.STRING) // pour stocké sur form string pas ordinal
    private StatusRDV status;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
