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

    //TODO: fikse responsene, legg til error nummer
    //TODO: ikke oppdater dersom score er dårlige enn forige gang
    //TODO: playerResponse bør være anderledes, ikke returnere null tenker jeg
    fun saveScore(score: Score): ResponseEntity<Any> {
        val event = eventRepository.findById(score.event.eventId.toInt())
        if (!event.isPresent) {
            return ResponseEntity("Event not found", HttpStatus.NOT_FOUND)
        }
        val playerResponse = playerService.addPlayer(score.player)
        if (playerResponse === null) {
            return ResponseEntity("Playername taken", HttpStatus.NOT_FOUND)
        }
        val savedScore = scoreRepository.save(score)
        return ResponseEntity(savedScore, HttpStatus.OK)
    }
}