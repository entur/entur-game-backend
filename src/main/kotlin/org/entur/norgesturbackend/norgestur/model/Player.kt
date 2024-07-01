package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "PLAYER")
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId")
    val playerId: Long = 0,
    @Column(name = "playerName", nullable = false)
    val playerName: String,
    @Column(name = "score")
    val score: Int? = null,
    @Column(name = "totalSteps")
    val totalSteps: Int? = null,
    @Column(name = "totalTravelTime")
    val totalTravelTime: Int? = null,
    @Column(name = "totalPlayTime")
    val totalPlayTime: Int? = null,
    @Column(name = "email", nullable = false)
    val email: String,
    @Column(name = "phoneNr", nullable = false)
    val phoneNr: String,
    @ManyToOne
    @JoinColumn(name = "eventId")
    val event: Event? = null
)
