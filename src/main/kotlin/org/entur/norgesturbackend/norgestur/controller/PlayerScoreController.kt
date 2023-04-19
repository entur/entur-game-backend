package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.service.PlayerScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerScoreController (val playerScoreService: PlayerScoreService){
    @GetMapping("/player-score")
    fun getPlayerScore(): List<PlayerScore> {
        return playerScoreService.getAll()
    }
}