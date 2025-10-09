package pe.edu.upc.noctuwellbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacientesDTO {
    private Long id;
    private Long userId;
    private Long planId;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String sexo;
    private Double peso;
    private Double altura;
    private String telefono;


}
