package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){
    fun getTopTenByDifficulty(difficulty: String): List<PlayerScore>{
        return playerScoreRepository.findTopTenScoresByDifficulty(difficulty)
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
        val optimalEasyRoute = 2
        val optimalEasyTravelTime = 27720
        val optimalMediumRoute = 6
        val optimalMediumTravelTime = 57780
        val optimalHardRoute = 7
        val optimalHardTravelTime = 123000
        val optimalEventRoute = 0
        val optimalEventTravelTime = 0

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
            score = 100.00 * (optimalEasyRoute.toDouble() / playerScore.totalOptions.toDouble()) * (optimalEasyTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "middels") {
            score = 100.00 * (optimalMediumRoute.toDouble() / playerScore.totalOptions.toDouble()) * (optimalMediumTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "vanskelig"){
            score = 100.00 * (optimalHardRoute.toDouble() / playerScore.totalOptions.toDouble()) * (optimalHardTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "event"){
            score = 100.00 * (optimalEventRoute.toDouble() / playerScore.totalOptions.toDouble()) * (optimalEventTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())
        }

        playerScore.score = score.toInt()
        playerScore.totalPlaytime = "$totalHoursPlayed:$totalMinutesPlayed:$totalSecondsPlayed"
        playerScore.totalTravelTime = "$totalHoursTraveled:$totalMinutesTraveled:$totalSecondsTraveled"
        return playerScoreRepository.save(playerScore)
    }
}