package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;

    private String day;
    private Time horaInicio;
    private Time horaFin;
    private Long specialistId;
}
