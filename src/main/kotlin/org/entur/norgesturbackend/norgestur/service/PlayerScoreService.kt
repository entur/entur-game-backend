package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){

    fun getTopTenByDifficulty(difficulty: String): List<PlayerScore>{
        return playerScoreRepository.findTopTenScoresByDifficulty(difficulty)
    }
    fun getTopTen(): List<PlayerScore>{
        return playerScoreRepository.findTopTenScores()
    }



    fun checkForExistingPlayer(playerScore: PlayerScore): HttpStatus {
        val matchingPlayer = playerScoreRepository.findByEmailNameAndPhoneNumber(playerScore.name, playerScore.email, playerScore.phoneNumber)
        val newPlayerScore: Int = calculateScore(playerScore)
        var response: HttpStatus = HttpStatus.BAD_REQUEST

        if (matchingPlayer == null){
            response = savePlayerScore(playerScore, newPlayerScore)
        } else if (matchingPlayer.name == playerScore.name && matchingPlayer.email == playerScore.email && matchingPlayer.phoneNumber == playerScore.phoneNumber){
            if (matchingPlayer.score < newPlayerScore){
                //playerScoreRepository.updatePlayer(playerScore.email, playerScore.name, playerScore.phoneNumber, newPlayerScore)
                matchingPlayer.score = newPlayerScore
                matchingPlayer.totalTravelTime = calculateTravelTime(playerScore.totalTravelTime.toInt())
                matchingPlayer.totalPlaytime = calculatePlayedTime(playerScore.totalPlaytime.toInt())
                matchingPlayer.totalOptions = playerScore.totalOptions

                playerScoreRepository.save(matchingPlayer)
                response = HttpStatus.OK
            }
        } else {
            response = HttpStatus.BAD_REQUEST
        }
        return response
    }

    fun calculateScore(playerScore: PlayerScore): Int{

        var score = 0.00

        if (playerScore.difficulty.lowercase() == "lett"){
            score = 100.00 * (OPTIMAL_EASY_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EASY_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "middels") {
            score = 100.00 * (OPTIMAL_MEDIUM_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_MEDIUM_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "vanskelig"){
            score = 100.00 * (OPTIMAL_HARD_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_HARD_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "event"){
            score = 100.00 * (OPTIMAL_EVENT_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EVENT_TAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        }
        return score.toInt()
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

    fun savePlayerScore(playerScore: PlayerScore, score: Int): HttpStatus{

        playerScore.score = score
        playerScore.totalPlaytime = calculatePlayedTime(playerScore.totalPlaytime.toInt())
        playerScore.totalTravelTime = calculateTravelTime(playerScore.totalTravelTime.toInt())

        playerScoreRepository.save(playerScore)

        return HttpStatus.CREATED
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