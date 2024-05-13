package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    FlightClient flightClient;

    private final List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public Ticket addTicket(Ticket ticket, Long id) {
        // Obtener el vuelo correspondiente por su ID
        FlightDto flightToTicket = flightClient.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight with id " + id + " not found"));

        // Establecer el FlightDto en el ticket
        ticket.setFlightDto(flightToTicket);

        //Agrego el ticket al array
        tickets.add(ticket);

        //Muestro el ticket
        return ticket;
    }
}
