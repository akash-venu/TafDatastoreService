package com.teckarch.TafDatastoreService.Service.Interfaces;

import com.teckarch.TafDatastoreService.Models.Bookings;

import java.util.List;

public interface BookingService {
    Bookings createBooking(Bookings bookings);
    List<Bookings> getAllBookings();
    Bookings getBookingById(Long bookingId);
    Bookings updateBooking(Long bookingId,Bookings updatedBookings);
    void deleteBooking(Long bookingId);
}
