package com.teckarch.TafDatastoreService.Repository;

import com.teckarch.TafDatastoreService.Models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {

}
