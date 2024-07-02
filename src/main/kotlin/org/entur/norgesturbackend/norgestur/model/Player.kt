package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "PLAYER")
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    val playerId: Long = 0,

    @Column(name = "player_name", nullable = false, unique = true)
    val playerName: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "phone_number", nullable = false, unique = true)
    val phoneNumber: String
)

