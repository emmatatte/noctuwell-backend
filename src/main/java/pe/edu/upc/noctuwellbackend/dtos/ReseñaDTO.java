package pe.edu.upc.noctuwellbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReseñaDTO {
    private Long id;
    private Long pacienteId;
    private Long especialistaId;
    private String comentario;
    private LocalDate fecha;
    private String calificacion;

}
