package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "PLAYER")
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId")
    val playerId: Long = 0,

    @Column(name = "playerName", nullable = false, unique = true)
    val playerName: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "phoneNumber", nullable = false, unique = true)
    val phoneNumber: String
)

