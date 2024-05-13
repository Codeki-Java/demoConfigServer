package codoacodo.vuelosapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    private String origen;
    private String destino;
    private String fechaHoraSalida;
    private String fechaHoraLlegada;
    private double precio;
    private String frecuencia;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Flight(String origen, String destino, String fechaHoraSalida, String fechaHoraLlegada, double precio, String frecuencia) {
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.precio = precio;
        this.frecuencia = frecuencia;

    }
}
