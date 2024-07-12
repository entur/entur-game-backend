package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Player
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class PlayerController(private val playerService: PlayerService) {

    @GetMapping("/player/all")
    fun getAllPlayers(): List<Player> {
        return playerService.getAllPlayers()
    }


    @GetMapping("/player/{playerName}")
    fun getPlayerByPlayerName(@PathVariable playerName: String): ResponseEntity<Any> {
        val player = playerService.getPlayerByPlayerName(playerName)
        return if (player != null) {
            ResponseEntity.ok(player)
        } else {
            ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to "Player not found"))
        }
    }

    @PostMapping("/new-player")
    fun createPlayer(@RequestBody player: Player): ResponseEntity<Any> {
        val savedPlayer = playerService.addPlayer(player)
        return if (savedPlayer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer)
        } else {
            ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to "Player with same name but different email and/or phone number already exists"))
        }
    }
}
