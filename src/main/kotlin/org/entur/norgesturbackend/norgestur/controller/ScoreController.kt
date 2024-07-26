package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.ScoreService
import org.springframework.http.ResponseEntity

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

    @GetMapping("/score/event/{eventId}")
    fun getScoresByEventId(@PathVariable eventId: Long): List<Score> {
        return scoreService.getScoresByEventId(eventId)
    }

    @PostMapping("/score/save")
    fun saveScore(
        @RequestBody score: Score,
    ): ResponseEntity<Any> {
        return scoreService.saveScore(score)
    }
}