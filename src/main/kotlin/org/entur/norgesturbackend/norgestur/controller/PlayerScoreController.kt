package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.model.PlayerScoreDto
import org.entur.norgesturbackend.norgestur.model.toResponse
import org.entur.norgesturbackend.norgestur.service.PlayerScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerScoreController (val playerScoreService: PlayerScoreService){
    @GetMapping("/player-score")
    fun getPlayerScore(): List<PlayerScoreDto> {
        return playerScoreService.getAll().map { it.toResponse() }
    }
    @PostMapping("/player-score")
    fun addPlayerScore(
            @RequestBody playerScore: PlayerScore
    ): PlayerScore{
        return playerScoreService.savePlayerScore(playerScore)
    }
}