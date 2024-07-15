package org.entur.norgesturbackend.norgestur.service

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

    //TODO: eksisterende event/player med nye score verdier -> suksess dvs. oppdateres
    //TODO: dersom score er lavere enn forige: suksess dvs. feilmelding
    //TODO: ny event/player kombo -> suksess dvs. ny event/player kombo legges til
    //TODO: dersom event ikke eksisterer -> suksess dvs. feilmelding
    //TODO: dersom player ikke eksisterer-> suksess dvs. ønske: legg til player og så legg til score)
    //TODO: dersom playerNavn eksisterer men med annen email og/eller tlf. nummer -> suksess dvs. feilmelding

    fun saveScore(score: Score): ResponseEntity<Any> {
        val event = eventRepository.findById(score.event.eventId.toInt())
        if (!event.isPresent) {
            return ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to "Event not found"))
        }
        val player = playerService.addPlayer(score.player)
        if (player === null) {
            return ResponseEntity.status(400).body(mapOf("status" to 400, "error" to "Bad Request", "message" to "Player with same name but different email and/or phone number already exists"))
        }
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