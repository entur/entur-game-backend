package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.config.MyProperties
import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.model.PlayerScoreDto
import org.entur.norgesturbackend.norgestur.model.toResponse
import org.entur.norgesturbackend.norgestur.service.PlayerScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PlayerScoreController (val playerScoreService: PlayerScoreService){

    @GetMapping("/player-score/{difficulty}")
    fun getPlayerScoreByDifficulty(
            @PathVariable difficulty: String,
            @RequestParam(defaultValue = "20") size: Number
    ): List<PlayerScoreDto> {
            return playerScoreService.getScoreByDifficultyAndSize(difficulty , size).map { it.toResponse() }
    }

    @PostMapping("/player-score")
    fun addPlayerScore(
            @RequestBody playerScore: PlayerScore,
    ): HttpStatus{
        return playerScoreService.savePlayerScore(playerScore)
    }
}