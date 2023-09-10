package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.GameModeRepository
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository, val gameModeRepository: GameModeRepository){

    fun getScoreByDifficultyAndSize(difficulty: String, size: Number): List<PlayerScore>{
        return playerScoreRepository.findScoreByDifficultyAndSize(difficulty, size)
    }
    fun getTopTen(): List<PlayerScore>{
        return playerScoreRepository.findTopTenScores()
    }

    fun calculateScore(playerScore: PlayerScore): Int{
        val gameMode = gameModeRepository.findGameModeByDifficulty(playerScore.difficulty)
        if(gameMode == null) return 0
        return (100.00 * (gameMode.optimalRoute.toDouble() / playerScore.totalOptions.toDouble()) * (gameMode.optimalTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())).toInt()
    }

    fun calculateTravelTime(totalTravelTime: Int): String{
        val hoursTravelTime = totalTravelTime / 3600
        val minutesTravelTime = (totalTravelTime % 3600) / 60
        val secondsTravelTime = totalTravelTime % 60

        return String.format("%02d:%02d:%02d", hoursTravelTime, minutesTravelTime, secondsTravelTime)
    }

    fun calculatePlayedTime(totalPlayedTime: Int): String{
        val hoursPlayTime = totalPlayedTime / 3600
        val minutesPlayTime = (totalPlayedTime % 3600) / 60
        val secondsPlayTime = totalPlayedTime % 60

        return String.format("%02d:%02d:%02d", hoursPlayTime, minutesPlayTime, secondsPlayTime)

    }

    fun savePlayerScore(playerScore: PlayerScore): HttpStatus{
        val matchingPlayer = playerScoreRepository.findByEmailNameAndPhoneNumber(playerScore.name, playerScore.email, playerScore.phoneNumber)
        val newPlayerScore: Int = calculateScore(playerScore)
        var response: HttpStatus = HttpStatus.BAD_REQUEST

        if (matchingPlayer == null){
            playerScore.score = calculateScore(playerScore)
            playerScore.totalPlaytime = calculatePlayedTime(playerScore.totalPlaytime.toInt())
            playerScore.totalTravelTime = calculateTravelTime(playerScore.totalTravelTime.toInt())
            playerScore.timesPlayed = 1

            playerScoreRepository.save(playerScore)
            response = HttpStatus.CREATED
        } else if (matchingPlayer.name == playerScore.name && matchingPlayer.email == playerScore.email && matchingPlayer.phoneNumber == playerScore.phoneNumber){
            if (matchingPlayer.score < newPlayerScore){
                matchingPlayer.score = newPlayerScore
                matchingPlayer.totalTravelTime = calculateTravelTime(playerScore.totalTravelTime.toInt())
                matchingPlayer.totalPlaytime = calculatePlayedTime(playerScore.totalPlaytime.toInt())
                matchingPlayer.totalOptions = playerScore.totalOptions
                matchingPlayer.timesPlayed ++

                playerScoreRepository.save(matchingPlayer)
                response = HttpStatus.OK
            }
        } else {
            response = HttpStatus.BAD_REQUEST
        }
        return response
    }
}