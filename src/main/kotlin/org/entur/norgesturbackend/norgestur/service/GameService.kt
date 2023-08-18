package org.entur.norgesturbackend.norgestur.service

import jakarta.transaction.Transactional
import org.entur.norgesturbackend.norgestur.model.GAME_STATUS
import org.entur.norgesturbackend.norgestur.model.Game
import org.entur.norgesturbackend.norgestur.model.Player
import org.entur.norgesturbackend.norgestur.repository.GameRepository
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.entur.norgesturbackend.norgestur.util.getRandomString
import org.springframework.stereotype.Service

@Service
class GameService(val gameRepository: GameRepository, val playerRepository: PlayerRepository){

    fun getAllGame(): List<Game> = gameRepository.findAll()

    fun getGame(id: String): Game = gameRepository.findById(id).orElseThrow()

    @Transactional
    fun createGame(game: Game): Game{
        var newId = getRandomString(8);

        while (gameRepository.existsById(newId)) {
            newId = getRandomString(8);
        }
        return gameRepository.saveAndFlush(game.copy(id = newId))
    }

    @Transactional
    fun joinGame(player: Player): Player{
        val gameId = player.game.id!!
        if(!gameRepository.existsById(gameId)) throw Exception("Game does not exist")
        return playerRepository.saveAndFlush(player)
    }

    @Transactional
    fun setGameFinished(id: String, name: String): Game{
        val game = gameRepository.findById(id).orElseThrow()
        val winnerPlayer = game.playerList.find { it.name == name}
        return gameRepository.save(game.copy(status = GAME_STATUS.FINISHED.name, winnerPlayer = winnerPlayer))
    }

    @Transactional
    fun setGameStart(id: String): Game {
        val game = gameRepository.findById(id).orElseThrow()
        return gameRepository.save(game.copy(status = GAME_STATUS.STARTED.name))
    }

    fun updateGame(updateGame: Game): Game{
        val game = gameRepository.findById(updateGame.id!!).orElseThrow()
        return gameRepository.save(game)
    }
}