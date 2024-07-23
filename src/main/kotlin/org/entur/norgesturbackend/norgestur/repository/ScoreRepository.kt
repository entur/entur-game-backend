package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

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

    fun findByEventAndPlayer(event: Event, player: Player): Score?

    fun deleteByEvent(event: Event)
}
