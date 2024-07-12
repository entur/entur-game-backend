package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ScoreRepository : JpaRepository<Score, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM score"
    )
    fun findAllScores(): List<Score>

    @Query(
        nativeQuery = true,
        value = """
            SELECT score.* FROM score 
            JOIN player ON player.player_id = score.player_id
            JOIN event ON event.event_id = score.event_id 
            WHERE event.is_active = true
            """
    )
    fun findScoresByActiveEvent(): List<Score>

    @Query(
        nativeQuery = true,
        value = """
            SELECT * FROM score 
            WHERE event_id = :eventId AND player_id = :playerId
            LIMIT 1
            """
    )
    fun getPlayerScoreEvent(@Param("eventId") eventId: Long, @Param("playerId") playerId: Long): Score?
}
