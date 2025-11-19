package pe.edu.upc.noctuwell.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "typeSpecialists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSpecialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "typeSpecialist", fetch = FetchType.LAZY)
    private List<Specialist> specialists;
}
