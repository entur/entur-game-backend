package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.config.MyProperties
import org.entur.norgesturbackend.norgestur.model.GameMode
import org.entur.norgesturbackend.norgestur.model.GameModeDto
import org.entur.norgesturbackend.norgestur.model.toDTO
import org.entur.norgesturbackend.norgestur.service.GameModeService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class GameModeController(val gameModeService: GameModeService, val myProperties: MyProperties) {


    @GetMapping("/game-mode/active-event")
    fun getEvent(): ResponseEntity<GameModeDto> {
        return when (val gameMode = gameModeService.getEvent()) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(gameMode.toDTO())

        }
    }

    @PostMapping("/game-mode")
    fun addGameMode(
        @RequestBody gameMode: GameMode,
        @RequestHeader("Auth") secret: String
    ): HttpStatus {
        if (secret != myProperties.secret) return HttpStatus.FORBIDDEN
        return gameModeService.saveGameMode(gameMode)
    }

    @GetMapping("/game-mode")
    fun getAllGameMode(): ResponseEntity<List<GameModeDto>> {
        val gameModeDTOs = gameModeService.getAllGameMode().map { it.toDTO() }
        return ResponseEntity.ok(gameModeDTOs)
    }

    @GetMapping("/game-mode/{difficulty}")
    fun getGameModeByDifficulty(
        @PathVariable difficulty: String,
    ): ResponseEntity<GameModeDto> {
        return when (val gameMode = gameModeService.getGameModeById(difficulty)) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(gameMode.toDTO())

        }
    }

    @GetMapping("/game-mode/optimal-route/{difficulty}")
    fun getOptimalRouteText(
        @PathVariable difficulty: String,
    ): ResponseEntity<String> {
        val responseHeaders = HttpHeaders()
        responseHeaders["Content-Type"] = "text/plain;charset=utf-8"
        return ResponseEntity.ok().headers(responseHeaders).body(gameModeService.getOptimalRouteText(difficulty))
    }


    @PutMapping("/game-mode/active-event/{difficulty}")
    fun updateActiveEvent(
        @PathVariable difficulty: String,
        @RequestHeader("Auth") secret: String
    ): HttpStatus {
        if (secret != myProperties.secret) return HttpStatus.FORBIDDEN
        return gameModeService.updateEvent(difficulty)
    }
}