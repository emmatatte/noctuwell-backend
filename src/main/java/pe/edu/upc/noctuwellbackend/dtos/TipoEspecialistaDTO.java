package pe.edu.upc.noctuwellbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEspecialistaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
