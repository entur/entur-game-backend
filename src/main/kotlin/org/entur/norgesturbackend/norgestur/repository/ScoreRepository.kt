package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ScoreRepository : JpaRepository<Score, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM score"
    )
    fun findAllScores(): List<Score>
}