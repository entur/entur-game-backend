package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.repository.PlayerScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PlayerScoreService (val playerScoreRepository : PlayerScoreRepository){

    fun getScoreByDifficultyAndSize(difficulty: String, size: Number): List<PlayerScore>{
        return playerScoreRepository.findScoreByDifficultyAndSize(difficulty, size)
    }
    fun getTopTen(): List<PlayerScore>{
        return playerScoreRepository.findTopTenScores()
    }

    fun getOptimalRouteText(difficulty: String): String{
        var optimalRoute = ""

        if (difficulty == "lett"){
            optimalRoute = "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + OPTIMAL_EASY_ROUTE + " og reisetid er " + calculateTravelTime(OPTIMAL_EASY_TRAVEL_TIME)
        } else if (difficulty == "middels"){
            optimalRoute = "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + OPTIMAL_MEDIUM_ROUTE + " og reisetid er " + calculateTravelTime(OPTIMAL_MEDIUM_TRAVEL_TIME)
        } else if (difficulty == "vanskelig"){
            optimalRoute = "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + OPTIMAL_HARD_ROUTE + " og reisetid er " + calculateTravelTime(OPTIMAL_HARD_TRAVEL_TIME)
        } else if (difficulty == "event"){
            optimalRoute = "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + OPTIMAL_EVENT_ROUTE + " og reisetid er " + calculateTravelTime(OPTIMAL_EVENT_TRAVEL_TIME)
        } else {
            optimalRoute = "Vanskelighetsgraden $difficulty finnes ikke i back-end."
        }
        return optimalRoute
    }



    fun calculateScore(playerScore: PlayerScore): Int{

        var score = 0.00

        if (playerScore.difficulty.lowercase() == "lett"){
            score = 100.00 * (OPTIMAL_EASY_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EASY_TRAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "middels") {
            score = 100.00 * (OPTIMAL_MEDIUM_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_MEDIUM_TRAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "vanskelig"){
            score = 100.00 * (OPTIMAL_HARD_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_HARD_TRAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
        } else if (playerScore.difficulty.lowercase() == "event"){
            score = 100.00 * (OPTIMAL_EVENT_ROUTE.toDouble() / playerScore.totalOptions.toDouble()) * (OPTIMAL_EVENT_TRAVEL_TIME.toDouble() / playerScore.totalTravelTime.toDouble())
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

    fun savePlayerScore(playerScore: PlayerScore): HttpStatus{
        playerScore.difficulty = playerScore.difficulty.lowercase()
        
        val matchingPlayer = playerScoreRepository.findByEmailNameAndPhoneNumber(playerScore.name, playerScore.email, playerScore.phoneNumber)
        val newPlayerScore: Int = calculateScore(playerScore)
        var response: HttpStatus = HttpStatus.BAD_REQUEST

        if (matchingPlayer == null){
            playerScore.score = calculateScore(playerScore)
            playerScore.totalPlaytime = calculatePlayedTime(playerScore.totalPlaytime.toInt())
            playerScore.totalTravelTime = calculateTravelTime(playerScore.totalTravelTime.toInt())

            playerScoreRepository.save(playerScore)
            response = HttpStatus.CREATED
        } else if (matchingPlayer.name == playerScore.name && matchingPlayer.email == playerScore.email && matchingPlayer.phoneNumber == playerScore.phoneNumber){
            if (matchingPlayer.score < newPlayerScore){
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

    companion object {
        const val OPTIMAL_EASY_ROUTE = 2
        const val OPTIMAL_EASY_TRAVEL_TIME = 27720
        const val OPTIMAL_MEDIUM_ROUTE = 6
        const val OPTIMAL_MEDIUM_TRAVEL_TIME = 57780
        const val OPTIMAL_HARD_ROUTE = 7
        const val OPTIMAL_HARD_TRAVEL_TIME = 123000
        const val OPTIMAL_EVENT_ROUTE = 7
        const val OPTIMAL_EVENT_TRAVEL_TIME = 7620
    }
}