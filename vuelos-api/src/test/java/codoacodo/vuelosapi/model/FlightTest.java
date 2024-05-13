package codoacodo.vuelosapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightTest {
   /* private Flight flight;

    @BeforeEach
    public void setUp(){
        System.out.println("Se esta creando el vuelo");
        flight = new Flight();
    }*/

    @Test
    public void setAndGetOriginTest(){
        Flight flight = new Flight();
        String testedOrigin = "COR";
        flight.setOrigen(testedOrigin);
        System.out.println("Se le asigna el origen : " + testedOrigin);
        assertEquals(testedOrigin, flight.getOrigen());
    }

    @Test public void setAndGetDestinyTest(){
        Flight flight = new Flight();
        String testedDestiny = "MDZ";
        flight.setDestino(testedDestiny);
        assertEquals(testedDestiny, flight.getDestino());
    }
}