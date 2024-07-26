package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlayerRepository : JpaRepository<Player, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player"
    )
    fun findAllPlayers(): List<Player>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player WHERE player_name = (:playerName)"
    )
    fun findPlayerByPlayerName(
        @Param("playerName") playerName: String
    ): Player?

    fun findPlayerByPlayerId(
        @Param("playerId") playerId: Long
    ): Player?

    @Query("SELECT p FROM Player p WHERE p.playerId NOT IN (SELECT DISTINCT s.player.playerId FROM Score s)")
    fun findPlayersWithNoScores(): List<Player>
}