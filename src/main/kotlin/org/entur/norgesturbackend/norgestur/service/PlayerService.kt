package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun getAllPlayers(): List<Player> {
        return playerRepository.findAllPlayers()
    }

    @Transactional
    fun addPlayer(player: Player): ResponseEntity<Any> {
        val existingPlayer = playerRepository.findPlayerByPlayerName(player.playerName)
        return if (existingPlayer != null) {
            if (existingPlayer.email == player.email && existingPlayer.phoneNumber == player.phoneNumber) {
                ResponseEntity.ok(existingPlayer)
            } else {
                ResponseEntity.status(400).body(mapOf("status" to 400, "error" to "Bad Request", "message" to "Player with same name but different email and/or phone number already exists"))
            }
        } else {
            val savedPlayer = playerRepository.save(player)
            ResponseEntity.ok(savedPlayer)
        }
    }
}
