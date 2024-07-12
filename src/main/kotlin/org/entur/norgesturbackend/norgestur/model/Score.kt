package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "SCORE", uniqueConstraints = [UniqueConstraint(columnNames = ["event_id", "player_id"])])
data class Score(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    val scoreId: Long = 0,

    @Column(name = "score_value", nullable = false)
    val scoreValue: Int,

    @Column(name = "total_step_number", nullable = false)
    val totalStepNumber: Int,

    @Column(name = "total_travel_time", nullable = false)
    val totalTravelTime: Int,

    @Column(name = "total_play_time", nullable = false)
    val totalPlayTime: Int,

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    val player: Player
)
