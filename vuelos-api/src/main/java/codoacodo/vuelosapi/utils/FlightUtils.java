package codoacodo.vuelosapi.utils;

import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {

    public List<Flight> detectOffers(List<Flight> flights, Integer offerPrice){

        //Usando programacion funcional
        return flights.stream()
                .filter(f ->f.getPrecio()<=offerPrice)
                .collect(Collectors.toList());
    }

    //Creacion de listado de FlightDto con stream
    public List<FlightDto> flightMapper(List<Flight> flights, double price) {
        return flights.stream()
                .map(f -> new FlightDto(
                        f.getId(),
                        f.getOrigen(),
                        f.getDestino(),
                        f.getFechaHoraSalida(),
                        f.getFechaHoraLlegada(),
                        f.getPrecio() * price))
                .collect(Collectors.toList());
    }
}
