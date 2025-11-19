package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime sentAt;
    private Long appointmentId;
    private Long patientId;
    private Long specialistId;
}
