package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){
    fun getAll(): List<PlayerScore>{
        return playerScoreRepository.findAll()
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

        val factor1 = 0.9
        val factor2 = 0.1
        val factor3 = 0.2
        val bestRoute1 = 7
        val bestRoute2 = 11594
        val bestRoute3 = 24960

        playerScore.score = 100 + ((factor1 * (bestRoute1 - playerScore.totalOptions)) + (factor3 * (bestRoute3 - playerScore.totalTravelTime.toInt()))).toInt()
        playerScore.totalPlaytime = "$totalHoursPlayed:$totalMinutesPlayed:$totalSecondsPlayed"
        playerScore.totalTravelTime = "$totalHoursTraveled:$totalMinutesTraveled:$totalSecondsTraveled"
        return playerScoreRepository.save(playerScore)
    }
}