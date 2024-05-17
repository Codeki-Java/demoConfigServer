package com.demo.ticketapi.controller;

import com.demo.ticketapi.model.FlightDto;
import com.demo.ticketapi.model.Ticket;
import com.demo.ticketapi.service.FlightClient;
import com.demo.ticketapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketservice;

    @Autowired
    private FlightClient flightClient;

    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketservice.getAllTickets();
    }

    @GetMapping("/vuelos")
    public List<FlightDto> getAllFlights(){
        return flightClient.getAllFlights();
    }

    //Agregado Ale
    @GetMapping("/vuelos/{id}")
    public Optional<FlightDto> findFlightById(@PathVariable Long id) {
        return flightClient.getFlightById(id);
    }
/*
    //addTicket original
    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket){
        return ticketservice.addTicket(ticket);
    }*/

    //METODO addTicket Ale
    @PostMapping("/add/{id}")
    public Ticket addTicket(@RequestBody Ticket ticket, @PathVariable Long id){
        return ticketservice.addTicket(ticket, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketservice.deleteTicket(id);
    }

    @PutMapping("/update/{id}")
    public Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
        return ticketservice.updateTicket(ticket, id);
    }


}
