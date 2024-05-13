package codoacodo.vuelosapi.repository;

import codoacodo.vuelosapi.model.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    private Flight flight;

    @BeforeEach
    void setup(){
        flight = new Flight("COR", "ROS","23/5/24","8:00",20000,"diaria");
    }

    @AfterEach
    void tearDown() {
        flightRepository.deleteAll(); // Limpiar todos los datos después de cada prueba
    }

    @Test
    void saveFlightTest(){
        //configuracion previa

        //llamar la funcionalidad
        Flight flightBD = flightRepository.save(flight);

        // verificar la salida o el comportamiento
        assertThat(flightBD).isNotNull();
        assertThat(flightBD.getId()).isGreaterThan(0);
    }

    @Test
    void flightFindByIdTest(){
        //configuracion previa: tener el vuelo guardado
        flightRepository.save(flight);

        //llamar a la funcionalidad
        Flight flightBD = flightRepository.findById(flight.getId()).get();

        //verificar
        assertThat(flightBD.getId()).isNotNull();
    }

    @Test
    void flightFindAllTest(){
        //configuracion previa: tener por lo menos 2 vuelos guardados
        Flight flight2 = new Flight("BUE","COR", "12/5/24","9:00",23000,"diaria");

        flightRepository.save(flight);
        flightRepository.save(flight2);

        //llamar a la funcionalidad
        List<Flight> flightList = flightRepository.findAll();

        //verificar
        assertThat(flightList).isNotNull();
        assertThat(flightList.size()).isEqualTo(2);
    }

    @Test
    void flightDeletedTest(){
        //configuracion previa
        flightRepository.save(flight);

        //llamar a la funcionalidad
        flightRepository.deleteById(flight.getId());

        //verificar: busco por id
        Optional<Flight> deletedFlight = flightRepository.findById(flight.getId());

        assertThat(deletedFlight).isEmpty();
    }

    @Test
    void flightUpdate(){
        //configuracion previa
        flightRepository.save(flight);
        Flight flightBD = flightRepository.findById(flight.getId()).get();

        flightBD.setOrigen("ROS");
        flightBD.setPrecio(18000);


        //llamar a la funcionalidad

        Flight flightUpdated = flightRepository.save(flightBD);

        //verificar
        assertThat(flightUpdated.getOrigen()).isEqualTo("ROS");
        assertThat(flightUpdated.getPrecio()).isEqualTo(18000);
    }

    @Test
    void findByOrigen() {
        //configuracion previa
        Flight flight2 = new Flight("BUE","MDZ", "12/5/24","9:00",23000,"diaria");

        flightRepository.save(flight);
        flightRepository.save(flight2);


        //llamar la funcionalidad
        List<Flight> flightFromBUE = flightRepository.findByOrigen("BUE");

        // verificar la salida o el comportamiento
        assertThat(flightFromBUE).isNotNull();
    }

    @Test
    void findByOrigenAndDestino() {
        Flight flight2 = new Flight("BUE","MDZ", "12/5/24","9:00",23000,"diaria");

        flightRepository.save(flight);
        flightRepository.save(flight2);

        //llamar la funcionalidad
        List<Flight> flightFromBUEToMDZ = flightRepository.findByOrigenAndDestino("BUE","MDZ");

        // verificar la salida o el comportamiento
        assertThat(flightFromBUEToMDZ).isNotNull();
    }
}
