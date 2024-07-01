package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "SCORE")
data class Score(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreId")
    val scoreId: Long = 0,

    @Column(name = "scoreValue", nullable = false)
    val scoreValue: Int,

    @Column(name = "totalStepNumber", nullable = false)
    val totalStepNumber: Int,

    @Column(name = "totalTravelTime", nullable = false)
    val totalTravelTime: Int,

    @Column(name = "totalPlayTime", nullable = false)
    val totalPlayTime: Int,

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    val event: Event,

    @ManyToOne
    @JoinColumn(name = "playerId", nullable = false)
    val player: Player
)
