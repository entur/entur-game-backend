package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game
)

data class PlayerResponse(
    val id: Int,
    val name: String,
)
data class PlayerRequest(
    val name: String,
    val gameId: String,
)

fun PlayerRequest.toDomain(): Player{
    return Player(
        id = null,
        name = name,
        game = Game(
            id = gameId,
            status = GAME_STATUS.WAITING_FOR_PLAYERS.name,
            playerList = emptyList(),
        )
    )
}

fun Player.toResponse(): PlayerResponse {
    return PlayerResponse(
        id = id!!,
        name = name,
    )
}