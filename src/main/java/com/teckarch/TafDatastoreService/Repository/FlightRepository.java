package com.teckarch.TafDatastoreService.Repository;

import com.teckarch.TafDatastoreService.Models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long> {

}
