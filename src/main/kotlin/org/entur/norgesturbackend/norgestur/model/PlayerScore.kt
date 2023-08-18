package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
data class PlayerScore(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val name: String,
        val email: String,
        val phoneNumber: Int,
        var score: Int,
        var difficulty: String,
        val totalOptions: Int,
        var totalPlaytime: String,
        var totalTravelTime: String,
        @ManyToOne
        @JoinColumn(name = "from_destination", referencedColumnName = "id")
        val fromDestination: Destination,
        @ManyToOne
        @JoinColumn(name = "to_destination", referencedColumnName = "id")
        val toDestination: Destination,
)

data class PlayerScoreDto(
        val name: String,
        val email: String,
        val phoneNumber: Int,
        val score: Int?,
        val difficulty: String,
        val totalOptions: Int,
        val totalPlaytime: String,
        val totalTravelTime: String,
        val fromDestination: Destination,
        val toDestination: Destination
)

fun PlayerScore.toResponse(): PlayerScoreDto {
        return PlayerScoreDto(
                name = name,
                email = email,
                phoneNumber = phoneNumber,
                score = score,
                difficulty = difficulty,
                totalOptions = totalOptions,
                totalPlaytime = totalPlaytime,
                totalTravelTime = totalTravelTime,
                fromDestination = fromDestination,
                toDestination = toDestination
        )

}
