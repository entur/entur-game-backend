package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.GameMode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface GameModeRepository : JpaRepository<GameMode, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM game_mode WHERE difficulty = (:difficulty)"
    )
    fun findGameModeByDifficulty(
        @Param("difficulty") difficulty: String
    ): GameMode?

    fun findGameModeByActiveEvent(activeEvent: Boolean): GameMode?
}