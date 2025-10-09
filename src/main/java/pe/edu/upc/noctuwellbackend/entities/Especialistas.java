package pe.edu.upc.noctuwellbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Especialistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String experiencia;
    private String certificaciones;
    private String descripcion;
    private String telefono;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "usuario_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_especialistas_user")
    )
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "tipoespecialista_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_especialistas_tipoespecialista")
    )
    private TipoEspecialista tipoEspecialista;

    @JsonIgnore
    @OneToMany(mappedBy = "especialistas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Horarios> horarios;
}
