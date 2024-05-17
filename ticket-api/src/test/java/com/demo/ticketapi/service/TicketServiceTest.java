package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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

        //Llamo la funcionalidad que se llamó al método getFlightById en el FlightClient simulado
        assertEquals(123L, addedTicket.getFlightDto().getId());
    }

    @Test
    void updateTicket() {

        //Genero el contexto:
           //Genero un dummyTicket con dummyFlightDto y lo guardo en una lista de dummyTicketsList

        FlightDto dummyFlightDto = new FlightDto();
        dummyFlightDto.setId(123L);
        dummyFlightDto.setOrigen("BUE");
        dummyFlightDto.setDestino("USH");
        dummyFlightDto.setFechaHoraSalida("hoy temprano");
        dummyFlightDto.setFechaHoraLlegada("hoy mas tarde");
        dummyFlightDto.setConvertedPrice(10.0);

        Ticket dummyTicket = new Ticket();
        dummyTicket.setId(11L);
        dummyTicket.setFlightDto(dummyFlightDto);
        dummyTicket.setPassengerName("nombre pasajero");
        dummyTicket.setPassengerEmail("email pasajero");
        dummyTicket.setPassengerPassport("pasaporte pasajero");

           //Agregar el dummyTicket a una lista
        List<Ticket> dummyTicketList = new ArrayList<>();
        dummyTicketList.add(dummyTicket);

           //Crear un nuevo ticket con información nueva
        Ticket updatedDummyTicket = new Ticket();
        updatedDummyTicket.setId(22L);
        updatedDummyTicket.setFlightDto(dummyFlightDto);
        updatedDummyTicket.setPassengerName("otro nombre");
        updatedDummyTicket.setPassengerEmail("otro email");
        updatedDummyTicket.setPassengerPassport("otro pasaporte");

           //Creo un objeto simulado de TicketService
        TicketService ticketServiceMock = mock(TicketService.class);

           // Configurar el comportamiento del mock para que devuelva un Ticket modificado cuando se llame a updateTicket
        when(ticketServiceMock.updateTicket(updatedDummyTicket,11L)).thenReturn(updatedDummyTicket);

        //Llamo la funcionalidad
        Ticket modifiedTicketResult = ticketServiceMock.updateTicket(updatedDummyTicket, 11L);

        //Verifico que el ticket modificado tiene los nuevos valores.
        assertEquals(22L, modifiedTicketResult.getId());
        assertEquals("otro nombre", modifiedTicketResult.getPassengerName());
        assertEquals("otro email", modifiedTicketResult.getPassengerEmail());
        assertEquals("otro pasaporte", modifiedTicketResult.getPassengerPassport());
    }
}