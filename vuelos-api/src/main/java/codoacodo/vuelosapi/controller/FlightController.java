package codoacodo.vuelosapi.controller;

import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import codoacodo.vuelosapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")

public class FlightController {

    @Autowired
    FlightService flightService;

    @CrossOrigin
    @GetMapping("")
    public List<FlightDto> getAllFlights() {
        return flightService.traerTodosLosVuelos();
    }

    @GetMapping("/{id}")
    public Optional<Flight> findFlightById(@PathVariable Long id) {
        return flightService.buscarVueloPorId(id);
    }

    //Paso el vuelo por el body del JSON y el companyId por parámetro
    @PostMapping("/agregar/{companyId}")
    public void createFlight(@RequestBody Flight flight, @PathVariable Long companyId) {
        flightService.crearVuelo(flight, companyId);
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.borrarVueloPorId(id);
    }

    @PutMapping("/actualizar")
    public Optional<Flight> updateFlight(@RequestBody Flight flight) {
        return flightService.actualizarVuelo(flight);
    }

    @GetMapping("/ofertas")
    public List<Flight> getOffers(@RequestParam (name="precio") int offerPrice) {
        return flightService.getOffers(offerPrice);
    }

    @GetMapping("/origen")
    public List<Flight> getByLocations(@RequestParam String origen) {
        return flightService.getByOrigen(origen);
    }

    @GetMapping("/locations")
    public List<Flight> getByLocations(@RequestParam String origen, @RequestParam String destino) {
        return flightService.getByOrigenAndDestino(origen, destino);
    }

    @GetMapping("/allDolars")
    public List<Dolar> getAllDolars(){
        return flightService.getAllDolars();
    }

}
