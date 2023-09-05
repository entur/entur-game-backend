package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.config.MyProperties
import org.entur.norgesturbackend.norgestur.model.GameMode
import org.entur.norgesturbackend.norgestur.service.GameModeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GameModeController (val gameModeService: GameModeService, val myProperties: MyProperties) {


    @GetMapping("/game-mode")
    fun getGameModeByDifficulty(
        @RequestParam difficulty: String,
    ): GameMode {
        return gameModeService.getGameModeById(difficulty)
    }

    @GetMapping("/game-mode/optimal-route/")
    fun getOptimalRouteText(
        @RequestParam difficulty: String
    ): String{
        return gameModeService.getOptimalRouteText(difficulty)
    }

    @PostMapping("game-mode")
    fun addGameMode(
        @RequestBody gameMode: GameMode,
        @RequestHeader("Auth") secret: String
    ): HttpStatus{
        if (secret != myProperties.secret) return HttpStatus.FORBIDDEN

        return gameModeService.saveGameMode(gameMode)
    }
}