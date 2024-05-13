package com.demo.ticketapi.service;

import com.demo.ticketapi.model.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "vuelos-api")
public interface FlightClient {

    @GetMapping("/vuelos")
    List<FlightDto> getAllFlights();

    @GetMapping("/vuelos/{id}")
    Optional<FlightDto> getFlightById();

}
