package com.teckarch.TafDatastoreService.Service;

import com.teckarch.TafDatastoreService.Models.Flights;
import com.teckarch.TafDatastoreService.Repository.FlightRepository;
import com.teckarch.TafDatastoreService.Service.Interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flights addFlight(Flights flights) {
        return flightRepository.save(flights);
    }

    @Override
    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flights getFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }


    @Override
    public Flights updateFlight(Flights flight) {
        return flightRepository.save(flight);  // Save the updated flight object
    }


    @Override
    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }
}
