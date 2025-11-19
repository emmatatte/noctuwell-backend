package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialistPostDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String certification;
    private String description;
    private Integer experience;
    private Long userId; // solo el ID del usuario existente
    private Long typeSpecialistId;
}
