package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.model.Score
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.entur.norgesturbackend.norgestur.repository.ScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ScoreService(
    private val scoreRepository: ScoreRepository,
    private val eventRepository: EventRepository,
    private val playerService: PlayerService
) {

    fun getAllScores(): List<Score> {
        return scoreRepository.findAllScores()
    }

    fun getActiveScores(): List<Score> {
        return scoreRepository.findScoresByActiveEvent()
    }

    fun getScoresByEventId(eventId: Long): List<Score> {
        return scoreRepository.findByEventId(eventId)
    }

    fun saveScore(score: Score): ResponseEntity<Any> {
        val event = eventRepository.findById(score.event.eventId)
        if (!event.isPresent) {
            return ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to "Event not found"))
        }

        val playerResponse = playerService.addPlayer(score.player)
        if (!playerResponse.statusCode.is2xxSuccessful) {
            return playerResponse
        }
        val player = playerResponse.body as Player

        val newScore = score.copy(player = score.player.copy(playerId = player.playerId))
        val existingScore = scoreRepository.findByEventAndPlayer(score.event, newScore.player)
        if (existingScore != null) {
            if (newScore.scoreValue <= existingScore.scoreValue) {
                return ResponseEntity.status(400).body(mapOf("status" to 400, "error" to "Bad Request", "message" to "New score is not higher than existing score"))
            }
            val updatedScore = existingScore.copy(
                scoreValue = newScore.scoreValue,
                totalStepNumber = newScore.totalStepNumber,
                totalTravelTime = newScore.totalTravelTime,
                totalPlayTime = newScore.totalPlayTime
            )

            scoreRepository.save(updatedScore)
            return ResponseEntity(updatedScore, HttpStatus.OK)
        }
        val savedScore = scoreRepository.save(newScore)
        return ResponseEntity(savedScore, HttpStatus.OK)
    }
}
