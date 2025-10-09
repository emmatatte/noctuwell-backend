package pe.edu.upc.noctuwellbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecialistasDTO {
    private Long id;
    private Long userId;
    private Long tipoEspecialistaId;
    private String nombre;
    private String apellido;
    private String experiencia;
    private String certificaciones;
    private String descripcion;
    private String telefono;
}
