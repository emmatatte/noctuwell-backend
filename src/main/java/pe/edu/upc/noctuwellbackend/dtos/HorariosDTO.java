package pe.edu.upc.noctuwellbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorariosDTO {
    private Long id;
    private Long especialistaId;
    private String dia_semana;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private Boolean disponibilidad;

}
