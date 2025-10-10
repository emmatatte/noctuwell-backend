package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String status;
    private Long patientId;
    private Long specialistId;
}