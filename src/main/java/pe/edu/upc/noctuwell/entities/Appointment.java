package pe.edu.upc.noctuwell.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String status; //Puede ser agendado, cancelado, programado o otros

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @JsonIgnore
    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private List<Message> messages;

    @JsonIgnore
    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private List<Diagnosis> diagnoses;
}
