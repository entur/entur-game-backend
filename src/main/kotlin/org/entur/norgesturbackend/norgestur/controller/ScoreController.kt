package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.ScoreService

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
}