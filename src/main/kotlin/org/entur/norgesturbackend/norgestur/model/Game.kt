package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

enum class GAME_STATUS(val status: String) {
    WAITING_FOR_PLAYERS("WAITING_FOR_PLAYERS"),
    STARTED("STARTED"),
    FINISHED("FINISHED")
}

@Entity
data class Game(
    @Id
    val id: String?,
    val status: String,
    @OneToMany
    @JoinColumn(name = "game_id")
    val playerList: List<Player>,
    @OneToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    val winnerPlayer: Player? = null,
    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    val owner: Player? = null
)

data class GameRequest(
    val playerList: List<PlayerRequest>,
    val owner: PlayerRequest?,
)

data class GameResponse(
    val id: String,
    val status: String,
    val playerList: List<PlayerResponse>
)

fun GameRequest.toDomain(gameStatus: String, winnerPlayer: PlayerRequest?): Game {
    return Game(
        id = null,
        status = GAME_STATUS.valueOf(gameStatus).name,
        playerList = playerList.map { it.toDomain() },
        winnerPlayer = winnerPlayer?.toDomain(),
        owner = owner?.toDomain()
    )
}

fun Game.toResponse(): GameResponse {
    if(id == null) throw Exception("Game id is null")
    return GameResponse(
        id = id,
        status = status,
        playerList = playerList.map { it.toResponse() }
    )
}