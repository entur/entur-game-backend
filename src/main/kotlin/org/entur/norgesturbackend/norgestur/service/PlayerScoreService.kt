package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){
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

        val optimalRoute = 3
        val optimalTravelTime = 7485
        val score  = 100.00 * (optimalRoute.toDouble() / playerScore.totalOptions.toDouble()) * (optimalTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())
        playerScore.score = score.toInt()

        playerScore.totalPlaytime = "$totalHoursPlayed:$totalMinutesPlayed:$totalSecondsPlayed"
        playerScore.totalTravelTime = "$totalHoursTraveled:$totalMinutesTraveled:$totalSecondsTraveled"
        return playerScoreRepository.save(playerScore)
    }
}