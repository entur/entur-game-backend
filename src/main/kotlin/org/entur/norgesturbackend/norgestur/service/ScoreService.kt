package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Score
import org.entur.norgesturbackend.norgestur.repository.ScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ScoreService(private val scoreRepository: ScoreRepository) {

    fun getAllScores(): List<Score> {
        return scoreRepository.findAllScores()
    }

    fun getActiveScores(): List<Score> {
        return scoreRepository.findScoresByActiveEvent()
    }

//    TODO: update if matchin player
    fun saveScore(score: Score): HttpStatus {
        val matchingPlayer = scoreRepository.getPlayerScoreEvent(score.event.eventId, score.player.playerId)
        if (matchingPlayer == null) {
            scoreRepository.save(score)
            return HttpStatus.OK
        }
        scoreRepository.save(score)
        return HttpStatus.BAD_REQUEST
    }
}