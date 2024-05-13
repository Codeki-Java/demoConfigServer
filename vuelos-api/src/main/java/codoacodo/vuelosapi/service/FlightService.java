package codoacodo.vuelosapi.service;

import codoacodo.vuelosapi.configuration.FlightConfiguration;
import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import codoacodo.vuelosapi.repository.CompanyRepository;
import codoacodo.vuelosapi.repository.FlightRepository;
import codoacodo.vuelosapi.utils.FlightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FlightConfiguration flightConfiguration;

    @Autowired
    FlightUtils flightUtils;


    public List<FlightDto> traerTodosLosVuelos() {
        double dolarPrice = getDolarPrice();
        List<Flight> flights = flightRepository.findAll();
        return flightUtils.flightMapper(flights, dolarPrice);
    }

    public void crearVuelo(Flight flight, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("flight", "id", companyId));

        //seteo compañia
        flight.setCompany(company);

        //guardo el vuelo
        flightRepository.save(flight);
    }

    public Optional<Flight> buscarVueloPorId(Long id) {
        return flightRepository.findById(id);
    }

    public void borrarVueloPorId(Long id) {
        flightRepository.deleteById(id);
    }

    public Optional<Flight> actualizarVuelo(Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findById(flight.getId());
    }

    public List<Flight> getOffers(Integer offerPrice) {
        //Traigo todos los vuelos
        List<Flight> flights= flightRepository.findAll();
        return flightUtils.detectOffers(flights, offerPrice);
    }

    //Version simple
    public List<Flight> getByOrigen(String origen){
        return flightRepository.findByOrigen(origen);
    }


    //Version usando .stream()
   /* public List<Flight> getByOrigen(String origen) {
        //Traigo todos los vuelos
        List<Flight> flights = flightRepository.findAll();

        //recorro los vuelos que traje y agrego los que tienen ese origen
        List<Flight> flightByOrigen = flights.stream()
                .filter(f -> f.getOrigen().equals(origen))
                .collect(Collectors.toList());

        return flightByOrigen;
    }*/

    public List<Flight> getByOrigenAndDestino(String origen, String destino){
        return flightRepository.findByOrigenAndDestino(origen, destino);
    }

    private double getDolarPrice() {
         Dolar dolar = flightConfiguration.fetchDolar();
         return dolar.getPromedio();
    }

    public List<Dolar> getAllDolars() {
         return List.of(flightConfiguration.fetchAllDolars());
    }
}
