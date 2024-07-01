package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlayerRepository : JpaRepository<Player, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player"
    )
    fun findAllPlayer(): List<Player>
}