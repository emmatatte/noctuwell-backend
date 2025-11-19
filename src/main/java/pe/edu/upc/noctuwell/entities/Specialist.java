package pe.edu.upc.noctuwell.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "specialists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String certification;
    private String description;
    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "typeEspecialist_id")
    private TypeSpecialist typeSpecialist;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<Diagnosis> diagnoses;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<Message> messages;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @JsonIgnore
    @OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY)
    private List<Review> reviews;
}

