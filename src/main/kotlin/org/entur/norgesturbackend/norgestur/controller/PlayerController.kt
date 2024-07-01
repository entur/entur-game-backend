package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Player
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.PlayerService

@RestController
class PlayerController(private val playerService: PlayerService) {

    @GetMapping("/player/all")
    fun getAllPlayers(): List<Player> {
        return playerService.getAllPlayers()
    }
}
