package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Journey(
    @Id
    @Column(name = "id")
    val id: Int,
    @Column(name = "name")
    val name: String,
    @Column(name = "startlocationid")
    val startLocationId: String,
    @Column(name = "endlocationid")
    val endLocationId: String,
    @Column(name = "starttime")
    val startTime: LocalDateTime,
    @Column(name = "optimaljourney")
    val optimalJourney: String,
    @Column(name = "optimaltraveltime")
    val optimalTravelTime: Int,
)
