package codoacodo.vuelosapi.utils;

import codoacodo.vuelosapi.configuration.FlightConfiguration;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FlightUtilsTest {
    private FlightUtils flightUtils;

    private FlightConfiguration flightConfiguration;

    @BeforeEach
    void setUp(){
        flightUtils = new FlightUtils(); // Asignar una instancia en setUp()
    }

    @AfterEach
    void tearDown() {
        // Limpiar datos creados durante la prueba, si es necesario
    }

    @Test
    void detectOffersTest() {

        //Creo 5 vuelos
        Flight flight1 = new Flight("BUE","MDZ", "4/5/24 10hs","4/5/24 11hs",32000,"semanal");
        Flight flight2 = new Flight("EZE","MDZ", "4/5/24 10hs","4/5/24 11hs",31000,"semanal");
        Flight flight3 = new Flight("COR","MDZ", "4/5/24 10hs","4/5/24 11hs",22000,"semanal");
        Flight flight4 = new Flight("ROS","BUE", "4/5/24 10hs","4/5/24 11hs",12000,"semanal");
        Flight flight5 = new Flight("COR","TUC", "4/5/24 10hs","4/5/24 11hs",15000,"semanal");

        //armo lista con los 5 vuelos
        List<Flight> longFlightList = new ArrayList<>();
        longFlightList.add(flight1);
        longFlightList.add(flight2);
        longFlightList.add(flight3);
        longFlightList.add(flight4);
        longFlightList.add(flight5);

        //llamo a la funcionalidad filtrando los vuelos <=20000
        List<Flight> listaFiltrada = flightUtils.detectOffers(longFlightList, 20000);

        //verifico
        assertEquals(listaFiltrada.size(),2);
    }

    @Test
    void flightMapperTest() {
        List<Flight> flightList;
        List<FlightDto> flightDtoList;
        double dolarPrice = 1015;

        //Creo 1 vuelo
     //   Flight flight1 = new Flight(1L,"BUE","NQN", "25/4/24 12hs", "25/4/24 13hs",20000, "semanal");

        Company company = new Company("AA", "logo1");
        Flight flight1 = new Flight(1L, "BUE", "NQN", "25/4/24 12hs", "25/4/24 13hs", 20000, "semanal", company);

        //armo lista con el vuelo
        flightList = new ArrayList<>();
        flightList.add(flight1);

        //llamo a la funcionalidad para convertir la lista a precio dolar
        flightDtoList = flightUtils.flightMapper(flightList, dolarPrice);

        FlightDto flightDto = flightDtoList.get(0);

        //verifico si el primer FlightDto tiene id 1
        assertEquals(1, flightDto.getId());
        assertEquals(flight1.getPrecio() * dolarPrice, flightDto.getConvertedPrice());
    }

    @Test
    void fetchDolarTest(){

        //Genero el contexto
        Dolar dummyDolar = new Dolar();

        dummyDolar.setMoneda("USD");
        dummyDolar.setCasa("tarjeta");
        dummyDolar.setNombre("Tarjeta");
        dummyDolar.setVenta(1000.00);
        dummyDolar.setCompra(1200.00);

        //creo un objeto simulado de FlightUtils
        FlightConfiguration mockedFlightConfiguration = mock(FlightConfiguration.class);

        when(mockedFlightConfiguration.fetchDolar()).thenReturn(dummyDolar);

        //Llamo la funcionalidad
        Dolar dolar = mockedFlightConfiguration.fetchDolar();

        //Verificaciones
      //  assertEquals(1100, dolar.getPromedio());
        verify(1100);
    }

}