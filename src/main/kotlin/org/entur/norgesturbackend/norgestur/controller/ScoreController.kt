package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Score
import org.entur.norgesturbackend.norgestur.model.ScoreDto
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.ScoreService
import org.springframework.http.HttpStatus

@RestController
class ScoreController(private val scoreService: ScoreService) {

    @GetMapping("/score/all")
    fun getAllScores(): List<Score> {
        return scoreService.getAllScores()
    }

    @GetMapping("/score/active")
    fun getActiveScores(): List<Score> {
        return scoreService.getActiveScores()
    }

    @PostMapping("/score/save")
    fun saveScore(
            @RequestBody score: Score,
    ): HttpStatus {
        return scoreService.saveScore(score)
    }
}