package org.entur.norgesturbackend.norgestur.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "EVENT")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    val eventId: Long = 0,

    @Column(name = "eventName", nullable = false)
    val eventName: String,

    @Column(name = "startLocationId", nullable = false)
    val startLocationId: String,

    @Column(name = "endLocationId", nullable = false)
    val endLocationId: String,

    @Column(name = "startTime", nullable = false)
    val startTime: LocalDateTime,

    @Column(name = "optimalStepNumber", nullable = false)
    val optimalStepNumber: Int,

    @Column(name = "optimalTravelTime", nullable = false)
    val optimalTravelTime: Int
)

