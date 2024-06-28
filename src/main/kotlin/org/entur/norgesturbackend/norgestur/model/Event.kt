package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Event(
    @Id
    val id: Int,
    val name: String,
)

@Entity
data class Journey(
    @Id
    val id: Int,
    val name: String,
    val startId: String,
    val stopId: String,
    val dateTime: LocalDateTime,
    val description: String,
    val optimalJourney: Int,
    val optimalTravelTime: Int,
)

data class EventJourneyDto(
    val eventId: Int,
    val eventName: String,
    val journeyId: Int,
    val startId: String,
    val stopId: String,
    val dateTime: LocalDateTime,
    val description: String,
    val optimalJourney: Int,
    val optimalTravelTime: Int,
)