package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.entur.norgesturbackend.norgestur.model.PlayerScoreDto
import org.entur.norgesturbackend.norgestur.model.toResponse
import org.entur.norgesturbackend.norgestur.service.PlayerScoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerScoreController (val playerScoreService: PlayerScoreService){
    @GetMapping("/player-score")
    fun getPlayerScoreByDifficulty(
            @RequestParam difficulty: String,
            @RequestParam size: Number
    ): List<PlayerScoreDto> {
        return if (size.toInt() == 0){
            playerScoreService.getScoreByDifficultyAndAmount(difficulty.lowercase(), 20).map { it.toResponse() }
        } else {
            playerScoreService.getScoreByDifficultyAndAmount(difficulty.lowercase(), size).map { it.toResponse() }
        }
    }
    @GetMapping("/player-score/top-ten-overall")
    fun getPlayerScore(): List<PlayerScoreDto> {
        return playerScoreService.getTopTen().map { it.toResponse() }
    }
    @PostMapping("/player-score")
    fun addPlayerScore(
            @RequestBody playerScore: PlayerScore
    ): PlayerScore{
        return playerScoreService.savePlayerScore(playerScore)
    }
}