package com.teckarch.TafDatastoreService.Controllers;

import com.teckarch.TafDatastoreService.Models.Flights;
import com.teckarch.TafDatastoreService.Service.FlightServiceImpl;
import com.teckarch.TafDatastoreService.Service.Interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datastore/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @GetMapping
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flights> getFlightById(@PathVariable Long flightId) {
        Flights flight = flightService.getFlightById(flightId);
        return flight != null ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
        Flights addedFlight = flightService.addFlight(flight);
        return ResponseEntity.ok(addedFlight);
    }

    // Get available seats for a specific flight
    @GetMapping("/{flightId}/availableSeats")
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable Long flightId) {
        Flights flight = flightService.getFlightById(flightId);
        if (flight != null) {
            return ResponseEntity.ok(flight.getAvailableSeats());  // Return the available seats
        }
        return ResponseEntity.notFound().build();  // Return 404 if the flight is not found
    }

    @PutMapping("/{flightId}/reduceSeats")
    public ResponseEntity<Void> reduceAvailableSeats(@PathVariable Long flightId) {
        Flights flight = flightService.getFlightById(flightId);
        if (flight != null && flight.getAvailableSeats() > 0) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);  // Reduce seats by 1
            flightService.updateFlight(flight);  // Save the updated flight record
            return ResponseEntity.noContent().build();  // Return 204 No Content
        } else {
            return ResponseEntity.badRequest().body(null);  // Return 400 if no seats are available
        }
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        String message = "Flight with id " + flightId + " has been deleted.";
        return ResponseEntity.ok(message);

    }

}
