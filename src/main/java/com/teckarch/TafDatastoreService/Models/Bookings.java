package com.teckarch.TafDatastoreService.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="booking_id", unique = true, nullable = false,length = 20)
    private Long bookingId;

    @Column(name="user_id",nullable = false)
    private Long userId;

    @Column(name="flight_id",nullable = false)
    private Long flightId;

    @Column(name="booking_status",nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
