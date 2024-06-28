package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Event(
    @Id
    @Column(name = "event_id")
    val eventId: Int,
    @Column(name = "name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id", referencedColumnName = "journey_id")
    val journey: Journey,
)

@Entity
data class Journey(
    @Id
    @Column(name = "journey_id")
    val journeyId: Int,
    val name: String,
    @Column(name = "startid")
    val startId: String,
    @Column(name = "stopid")
    val stopId: String,
    @Column(name = "datetime")
    val dateTime: LocalDateTime,
    @Column(name = "description")
    val description: String,
    @Column(name = "optimaljourney")
    val optimalJourney: Int,
    @Column(name = "optimaltraveltime")
    val optimalTravelTime: Int,
)