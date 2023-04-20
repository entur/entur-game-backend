package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
data class PlayerScore(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val nickname: String,
        val score: Int,
        val totalOptions: Int,
        val totalPlaytime: Int,
        val totalTravelTime: Int,
        @ManyToOne
        @JoinColumn(name = "from_destination", referencedColumnName = "id")
        val fromDestination: Destination,
        @ManyToOne
        @JoinColumn(name = "to_destination", referencedColumnName = "id")
        val toDestination: Destination,
)

data class PlayerScoreDto(
        val nickname: String,
        val score: Int?,
        val totalOptions: Int,
        val totalPlaytime: String,
        val totalTravelTime: String,
        val fromDestination: Destination,
        val toDestination: Destination
)

fun PlayerScore.toResponse(): PlayerScoreDto {
       val minutesPlayTime = totalPlaytime/60
       val secondsPlayTime = totalPlaytime%60
       val totalPT = "$minutesPlayTime:$secondsPlayTime"
        val hoursTravelTime = totalTravelTime/3600
        val minutesTravelTime = (totalTravelTime % 3600)/60
        val secondsTravelTime = totalTravelTime%60
        val totalTT = "$hoursTravelTime:$minutesTravelTime:$secondsTravelTime"
        val factor1 = 0.6
        val factor2 = 0.1
        val factor3 = 0.4
        val playerScore = (factor1 * totalOptions) + (factor2 * totalPlaytime) + (factor3 * totalTravelTime)
        return PlayerScoreDto(
                nickname = nickname,
                score = playerScore.toInt(),
                totalOptions = totalOptions,
                totalPlaytime = totalPT,
                totalTravelTime = totalTT,
                fromDestination = fromDestination,
                toDestination = toDestination
        )

}
