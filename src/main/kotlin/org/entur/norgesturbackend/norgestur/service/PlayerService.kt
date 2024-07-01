package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun getAllPlayers(): List<Player> {
        return playerRepository.findAllPlayers()
    }
}
