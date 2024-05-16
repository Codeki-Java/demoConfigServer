package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteTicket(Long id) {
        // Buscar el ticket por id usando findFirst
        Optional<Ticket> ticketToDelete = tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst();

        // Si se encuentra el ticket, elimínalo de la lista
        ticketToDelete.ifPresent(tickets::remove);
    }

}
