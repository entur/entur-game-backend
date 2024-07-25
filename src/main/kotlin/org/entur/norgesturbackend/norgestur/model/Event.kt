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
    var startTime: LocalDateTime,

    @Column(name = "optimal_step_number", nullable = false)
    var optimalStepNumber: Int,

    @Column(name = "optimal_travel_time", nullable = false)
    var optimalTravelTime: Int,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = true,

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "player_id")
    var winner: Player? = null

) {
    fun updateWith(event: Event) {
        this.startTime = event.startTime
        this.optimalStepNumber = event.optimalStepNumber
        this.optimalTravelTime = event.optimalTravelTime
        this.isActive = event.isActive
        this.winner = event.winner
    }
}
