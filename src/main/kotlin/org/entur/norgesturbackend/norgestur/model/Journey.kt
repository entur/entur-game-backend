package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "JOURNEY")
data class Journey(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journeyId")
    val journeyId: Long = 0,
    @Column(name = "journeyName", nullable = false)
    val journeyName: String,
    @Column(name = "startLocationId", nullable = false)
    val startLocationId: String,
    @Column(name = "endLocationId", nullable = false)
    val endLocationId: String,
    @Column(name = "startTime", nullable = false)
    val startTime: LocalDateTime,
    @Column(name = "optimalJourney", nullable = false)
    val optimalJourney: Int,
    @Column(name = "optimalTravelTime", nullable = false)
    val optimalTravelTime: Int
)
