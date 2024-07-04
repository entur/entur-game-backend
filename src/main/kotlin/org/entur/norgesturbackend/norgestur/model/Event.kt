package org.entur.norgesturbackend.norgestur.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "EVENT")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    val eventId: Long = 0,

    @Column(name = "event_name", nullable = false)
    val eventName: String,

    @Column(name = "start_location_id", nullable = false)
    val startLocationId: String,

    @Column(name = "end_location_id", nullable = false)
    val endLocationId: String,

    @Column(name = "start_time", nullable = false)
    val startTime: LocalDateTime,

    @Column(name = "optimal_step_number", nullable = false)
    val optimalStepNumber: Int,

    @Column(name = "optimal_travel_time", nullable = false)
    val optimalTravelTime: Int,

    @Column(name = "is_active", nullable = false)
    val isActive: Boolean
)

//TODO: check if DTO is necessary
