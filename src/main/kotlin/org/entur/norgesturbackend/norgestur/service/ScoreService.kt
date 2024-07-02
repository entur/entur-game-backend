package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Score
import org.entur.norgesturbackend.norgestur.repository.ScoreRepository
import org.springframework.stereotype.Service

@Service
class ScoreService(private val scoreRepository: ScoreRepository) {

    fun getAllScores(): List<Score> {
        return scoreRepository.findAllScores()
    }
}