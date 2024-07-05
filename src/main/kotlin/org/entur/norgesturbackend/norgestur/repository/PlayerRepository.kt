package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlayerRepository : JpaRepository<Player, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player"
    )
    fun findAllPlayers(): List<Player>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player WHERE player_name = (:playerName) OR email = (:email) OR phone_number = (:phoneNumber)"
    )
    fun findByEmailNameAndPhoneNumber(
        @Param("playerName") playerName: String,
        @Param("email") email: String,
        @Param("phoneNumber") phoneNumber: String,
    ): Player?
}