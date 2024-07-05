package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Score
import org.entur.norgesturbackend.norgestur.model.ScoreDto
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.entur.norgesturbackend.norgestur.repository.ScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ScoreService(
    private val scoreRepository: ScoreRepository,
    private val playerRepository: PlayerRepository,
    private val eventRepository: EventRepository
) {

    fun getAllScores(): List<Score> {
        return scoreRepository.findAllScores()
    }

    fun getActiveScores(): List<Score> {
        return scoreRepository.findScoresByActiveEvent()
    }

//    fun calculateScore(playerScore: PlayerScore): Int{
//        val gameMode = gameModeRepository.findGameModeByDifficulty(playerScore.difficulty)
//        if(gameMode == null) return 0
//        return (100.00 * (gameMode.optimalRoute.toDouble() / playerScore.totalOptions.toDouble()) * (gameMode.optimalTravelTime.toDouble() / playerScore.totalTravelTime.toDouble())).toInt()
//    }
//
//    fun calculateTravelTime(totalTravelTime: Int): String{
//        val hoursTravelTime = totalTravelTime / 3600
//        val minutesTravelTime = (totalTravelTime % 3600) / 60
//        val secondsTravelTime = totalTravelTime % 60
//
//        return String.format("%02d:%02d:%02d", hoursTravelTime, minutesTravelTime, secondsTravelTime)
//    }
//
//    fun calculatePlayedTime(totalPlayedTime: Int): String{
//        val hoursPlayTime = totalPlayedTime / 3600
//        val minutesPlayTime = (totalPlayedTime % 3600) / 60
//        val secondsPlayTime = totalPlayedTime % 60
//
//        return String.format("%02d:%02d:%02d", hoursPlayTime, minutesPlayTime, secondsPlayTime)
//
//    }

    fun saveScore(score: Score): HttpStatus {
        var response: HttpStatus = HttpStatus.BAD_REQUEST
        val activeEventScore = scoreRepository.findScoresByActiveEventAndEventId(score.event.eventId)
            ?: return HttpStatus.NOT_FOUND

        val matchingPlayer = playerRepository.findByEmailNameAndPhoneNumber(
            score.player.email,
            score.player.playerName,
            score.player.phoneNumber
        )
        val newPlayerScore = 250 // calculate score

        if (matchingPlayer == null) {
            score.scoreValue = 250
            score.player = playerRepository.save(score.player)
            scoreRepository.save(score)
            response = HttpStatus.CREATED
        } else if (matchingPlayer.playerName == score.player.playerName && matchingPlayer.email == score.player.email && matchingPlayer.phoneNumber == score.player.phoneNumber) {
            val existingScore = scoreRepository.findByEventAndPlayer(score.event, score.player)

            if (existingScore != null) {
                if (existingScore.scoreValue < newPlayerScore) {
                    existingScore.scoreValue = newPlayerScore
                    existingScore.totalStepNumber = score.totalStepNumber
                    existingScore.totalTravelTime = score.totalTravelTime // calculate travel time
                    existingScore.totalPlayTime = score.totalPlayTime // calculate play time and convert to

                    scoreRepository.save(existingScore)
                    response = HttpStatus.OK
                }
            } else {
                score.scoreValue = newPlayerScore
                score.player = matchingPlayer
                scoreRepository.save(score)
                response = HttpStatus.CREATED
            }
        }
        scoreRepository.save(score)
        return HttpStatus.CREATED
    }
}