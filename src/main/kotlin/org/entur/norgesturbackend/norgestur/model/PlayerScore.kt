package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

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

data class PlayerScoreDto(
        val nickname: String,
        val score: Int,
        val totalOptions: Int,
        val totalPlaytime: String,
        val totalTravelTime: String,
        val fromDestination: String,
        val toDestination: String
)

fun PlayerScore.toResponse(): PlayerScoreDto {
       val minutesPlayTime = totalPlaytime/60
       val secondsPlayTime = totalPlaytime%60
       val totalPT = "$minutesPlayTime:$secondsPlayTime"
        val hoursTravelTime = totalTravelTime/3600
        val minutesTravelTime = (totalTravelTime % 3600)/60
        val secondsTravelTime = totalTravelTime%60
        val totalTT = "$hoursTravelTime:$minutesTravelTime:$secondsTravelTime"
        return PlayerScoreDto(
                nickname = nickname,
                score = score,
                totalOptions = totalOptions,
                totalPlaytime = totalPT,
                totalTravelTime = totalTT,
                fromDestination = fromDestination,
                toDestination = toDestination
        )

}
