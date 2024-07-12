package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun getAllPlayers(): List<Player> {
        return playerRepository.findAllPlayers()
    }

    fun getPlayerByPlayerName(playerName: String): Player? {
        return playerRepository.findPlayerByPlayerName(playerName)
    }

    @Transactional
    fun addPlayer(player: Player): Player? {
        val existingPlayer = playerRepository.findPlayerByPlayerName(player.playerName)
        return if (existingPlayer != null) {
            if (existingPlayer.email == player.email && existingPlayer.phoneNumber == player.phoneNumber) {
                existingPlayer
            } else {
                null
            }
        } else {
            playerRepository.save(player)
        }
    }
}
