package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Ticket updateTicket(Ticket ticket, Long id) {
        // Obtener el ticket correspondiente por su ID
        Optional<Ticket> optionalTicketToUpdate = tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        //Si encuentra el ticket, guardarlo con la nueva informacion
        if (optionalTicketToUpdate.isPresent()) {
            Ticket ticketToUpdate = optionalTicketToUpdate.get();

            //Actualizar campos del ticket con la nueva informacion
            ticketToUpdate.setId(ticket.getId());
            ticketToUpdate.setFlightDto(ticket.getFlightDto());
            ticketToUpdate.setPassengerName(ticket.getPassengerName());
            ticketToUpdate.setPassengerEmail(ticket.getPassengerEmail());
            ticketToUpdate.setPassengerPassport(ticket.getPassengerPassport());

            return ticketToUpdate;
        } else {
            throw new RuntimeException("Ticket con ID " + id + "no encontrado");
        }
    }
}
