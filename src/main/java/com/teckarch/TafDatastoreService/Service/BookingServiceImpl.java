package com.teckarch.TafDatastoreService.Service;

//import com.teckarch.TafDatastoreService.Models.Bookings;
//import com.teckarch.TafDatastoreService.Repository.BookingRepository;
//import com.teckarch.TafDatastoreService.Repository.FlightRepository;
//import com.teckarch.TafDatastoreService.Repository.UserRespository;
//import com.teckarch.TafDatastoreService.Service.Interfaces.BookingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private UserRespository userRepository;
//
//    @Autowired
//    private FlightRepository flightRepository;
//
//    @Override
//    public Bookings createBooking(Bookings bookings) {
//        return bookingRepository.save(bookings);
//    }
//
//    @Override
//    public List<Bookings> getAllBookings() {
//        return bookingRepository.findAll();
//    }
//
//    @Override
//    public Bookings getBookingById(Long bookingId) {
//        return bookingRepository.findById(bookingId).orElse(null);
//    }
//
//    @Override
//    public Bookings updateBooking(Long bookingId, Bookings updatedBookings) {
//        Bookings existingBooking = bookingRepository.findById(bookingId).orElse(null);
//        if (existingBooking != null) {
//            return bookingRepository.save(updatedBookings);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteBooking(Long bookingId) {
//        bookingRepository.findById(bookingId).ifPresent(booking -> {
//            booking.setStatus("Cancelled");
//            bookingRepository.save(booking);  // Mark as cancelled instead of deleting.
//        });
//
//        }
//}

import com.teckarch.TafDatastoreService.Models.Bookings;
import com.teckarch.TafDatastoreService.Models.Flights;
import com.teckarch.TafDatastoreService.Models.Users;
import com.teckarch.TafDatastoreService.Repository.BookingRepository;
import com.teckarch.TafDatastoreService.Repository.FlightRepository;
import com.teckarch.TafDatastoreService.Repository.UserRespository;
import com.teckarch.TafDatastoreService.Service.Interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRespository userRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Bookings createBooking(Bookings booking) {
        // Validate user
        Users user = userRepository.findById(booking.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + booking.getUserId()));

        // Validate flight and check seat availability
        Flights flight = flightRepository.findById(booking.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + booking.getFlightId()));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Flight is fully booked. No seats available.");
        }

        // Reduce available seats
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        // Save booking
        booking.setStatus("Booked");
        return bookingRepository.save(booking);
    }

    @Override
    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Bookings getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }

    @Override
    public Bookings updateBooking(Long bookingId, Bookings updatedBooking) {
        Bookings existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        // Update booking details (except ID and createdAt fields)
        existingBooking.setFlightId(updatedBooking.getFlightId());
        existingBooking.setUserId(updatedBooking.getUserId());
        existingBooking.setStatus(updatedBooking.getStatus());
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        // Mark the booking as cancelled
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
    }
}
