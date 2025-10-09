package pe.edu.upc.noctuwellbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name="TipoEspecialista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEspecialista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoEspecialista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Especialistas> especialistas;


}
