package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val nickname: String,
    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game
)

data class PlayerResponse(
    val id: Int,
    val nickname: String,
)
data class PlayerRequest(
    val nickname: String,
    val gameId: String,
)

fun PlayerRequest.toDomain(): Player{
    return Player(
        id = null,
        nickname = nickname,
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
        nickname = nickname,
    )
}