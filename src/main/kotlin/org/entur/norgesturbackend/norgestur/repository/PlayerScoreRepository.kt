package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlayerScoreRepository : JpaRepository<PlayerScore, Int>{
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score WHERE difficulty = (:difficulty) ORDER BY score DESC LIMIT (:size)"
    )
    fun findScoreByDifficultyAndAmount(
            @Param("difficulty") difficulty: String,
            @Param("size") size: Number
    ): List<PlayerScore>
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score ORDER BY score DESC LIMIT 10"
    )
    fun findTopTenScores(): List<PlayerScore>
}