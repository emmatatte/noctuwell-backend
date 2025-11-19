package pe.edu.upc.noctuwell.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    private Long id;
    private String background;
    private String allergies;
    private String medications;
    private Long patientId;
}
