package codoacodo.vuelosapi.repository;

import codoacodo.vuelosapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

   //Se usa si en el service uso la version sin .stream()
   List<Flight> findByOrigen(String origen);
   List<Flight> findByOrigenAndDestino(String origen, String destino);
}
