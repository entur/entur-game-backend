package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Time

@Entity
data class PlayerScore(
        @Id
        val id: Int,
        val nickname: String,
        val score: Int,
        val totalOptions: Int,
        val totalPlaytime: Int,
        val totalTravelTime: Int,
        val fromDestination: String,
        val toDestination: String
)
