package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDTO {
    private Long id;
    private String description;
    private String type;
    private String recommendations;
    private LocalDate date;
    private Long appointmentId;
    private Long historyId;
    private Long specialistId;
}
