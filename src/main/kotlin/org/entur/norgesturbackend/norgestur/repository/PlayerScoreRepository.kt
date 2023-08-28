package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus

interface PlayerScoreRepository : JpaRepository<PlayerScore, Int>{
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score WHERE difficulty = (:difficulty) ORDER BY score DESC LIMIT (:size)"
    )
    fun findScoreByDifficultyAndSize(
            @Param("difficulty") difficulty: String,
            @Param("size") size: Number
    ): List<PlayerScore>
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM player_score ORDER BY score DESC LIMIT 10"
    )
    fun findTopTenScores(): List<PlayerScore>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM player_score WHERE name = (:name) OR email = (:email) OR phone_number = (:phoneNumber)"
    )
    fun findByEmailNameAndPhoneNumber(
        @Param("name") name: String,
        @Param("email") email: String,
        @Param("phoneNumber") phoneNumber: Number,
        ): PlayerScore?
}