package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketService ticketService;

    @Test
    void addTicket() {

        //Genero el contexto creando un vuelo ficticio
        FlightDto dummyFlightDto = new FlightDto();

        dummyFlightDto.setId(123L);
        dummyFlightDto.setOrigen("BUE");
        dummyFlightDto.setDestino("USH");
        dummyFlightDto.setFechaHoraSalida("hoy temprano");
        dummyFlightDto.setFechaHoraLlegada("hoy mas tarde");
        dummyFlightDto.setConvertedPrice(10.0);

        //creo un objeto simulado de FlightClient
        FlightClient mockedFlightClient = mock(FlightClient.class);

        when(mockedFlightClient.getFlightById(123L)).thenReturn(Optional.of(dummyFlightDto));

        // Creo una instancia de TicketService y le asigno el mockedFlightClient a la propiedad flighClient
        TicketService ticketService = new TicketService();
        ticketService.flightClient = mockedFlightClient;

        //Llamo la funcionalidad con el id del dummyFlightDto
        Ticket addedTicket = ticketService.addTicket(new Ticket(), 123L);

        // Verificar que se llamó al método getFlightById en el FlightClient simulado
        assertEquals(123L, addedTicket.getFlightDto().getId());

    }
}