package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){

    fun getScoreByDifficultyAndAmount(difficulty: String, size: Number): List<PlayerScore>{
        return playerScoreRepository.findScoreByDifficultyAndAmount(difficulty, size)
    }
    fun getTopTen(): List<PlayerScore>{
        return playerScoreRepository.findTopTenScores()
    }

    fun savePlayerScore(playerScore: PlayerScore): PlayerScore{

        val hoursPlayTime = playerScore.totalPlaytime.toInt()/3600
        val minutesPlayTime = (playerScore.totalPlaytime.toInt() % 3600)/60
        val secondsPlayTime = playerScore.totalPlaytime.toInt()%60

        val hoursTravelTime = playerScore.totalTravelTime.toInt()/3600
        val minutesTravelTime = (playerScore.totalTravelTime.toInt() % 3600)/60
        val secondsTravelTime = playerScore.totalTravelTime.toInt()%60
        var score = 0.00


        val totalHoursPlayed: String = if (hoursPlayTime <= 9){
            "0$hoursPlayTime"
        } else {
            hoursPlayTime.toString()
        }
        val totalMinutesPlayed: String = if (minutesPlayTime <= 9){
            "0$minutesPlayTime"
        } else {
            minutesPlayTime.toString()
        }
        val totalSecondsPlayed: String = if (secondsPlayTime <= 9){
            "0$secondsPlayTime"
        } else {
            secondsPlayTime.toString()
        }

        val totalHoursTraveled: String = if (hoursTravelTime <= 9){
            "0$hoursTravelTime"
        } else {
            hoursTravelTime.toString()
        }
        val totalMinutesTraveled: String = if (minutesTravelTime <= 9){
            "0$minutesTravelTime"
        } else {
            minutesTravelTime.toString()
        }
        val totalSecondsTraveled: String = if (secondsTravelTime <= 9){
            "0$secondsTravelTime"
        } else {
            secondsTravelTime.toString()
        }

        if (playerScore.difficulty.lowercase() == "lett"){
            score = 100.00 * (OPTIMAL_EASY_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EASY_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "middels") {
            score = 100.00 * (OPTIMAL_MEDIUM_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_MEDIUM_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "vanskelig"){
            score = 100.00 * (OPTIMAL_HARD_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_HARD_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "event"){
            score = 100.00 * (OPTIMAL_EVENT_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EVENT_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        }

        playerScore.score = score.toInt()
        playerScore.totalPlaytime = "$totalHoursPlayed:$totalMinutesPlayed:$totalSecondsPlayed"
        playerScore.totalTravelTime = "$totalHoursTraveled:$totalMinutesTraveled:$totalSecondsTraveled"
        return playerScoreRepository.save(playerScore)
    }

    companion object {
        const val OPTIMAL_EASY_ROUTE = 2
        const val OPTIMAL_EASY_TAVEL_TIME = 27720
        const val OPTIMAL_MEDIUM_ROUTE = 6
        const val OPTIMAL_MEDIUM_TAVEL_TIME = 57780
        const val OPTIMAL_HARD_ROUTE = 7
        const val OPTIMAL_HARD_TAVEL_TIME = 123000
        const val OPTIMAL_EVENT_ROUTE = 0
        const val OPTIMAL_EVENT_TAVEL_TIME = 0
    }
}