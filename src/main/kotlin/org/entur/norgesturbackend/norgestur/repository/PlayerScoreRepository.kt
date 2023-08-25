package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlayerScoreRepository : JpaRepository<PlayerScore, Int>{
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score WHERE LOWER(difficulty) = (:difficulty) ORDER BY score DESC LIMIT (:amount)"
    )
    fun findScoreByDifficultyAndAmount(
            @Param("difficulty") difficulty: String,
            @Param("amount") amount: Number
    ): List<PlayerScore>
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score ORDER BY score DESC LIMIT 10"
    )
    fun findTopTenScores(): List<PlayerScore>
}