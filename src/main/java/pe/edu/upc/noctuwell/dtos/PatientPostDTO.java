package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientPostDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer weight;
    private Integer height;
    private String phone;
    private LocalDate birthDate;
    private Long userId; // solo el ID del usuario al que pertenece
}
