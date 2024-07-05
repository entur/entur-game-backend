package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "SCORE")
data class Score(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    val scoreId: Long = 0,

    @Column(name = "score_value", nullable = false)
    var scoreValue: Int,

    @Column(name = "total_step_number", nullable = false)
    var totalStepNumber: Int,

    @Column(name = "total_travel_time", nullable = false)
    var totalTravelTime: Int,

    @Column(name = "total_play_time", nullable = false)
    var totalPlayTime: Int,

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    var player: Player
)

// Probably need to use this for request type in score controller
data class ScoreDto(
    val scoreId: Long,
    val scoreValue: Int,
    val totalStepNumber: Int,
    val totalTravelTime: Int,
    val totalPlayTime: Int,
    val eventId: Long,
    val player: Player
)
