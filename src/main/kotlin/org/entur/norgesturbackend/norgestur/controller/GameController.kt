package org.entur.norgesturbackend.norgestur.controller


import org.entur.norgesturbackend.norgestur.model.*
import org.entur.norgesturbackend.norgestur.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/game")
class GameController(val gameService: GameService, val simpMessagingTemplate: SimpMessagingTemplate) {


    @GetMapping()
    fun getAllGames(): List<GameResponse> {
        return gameService.getAllGame().map { it.toResponse() }
    }


    @GetMapping("/{id}")
    fun getGame(@PathVariable id: String): GameResponse {
        val game = gameService.getGame(id)
        return game.toResponse()
    }

    @PostMapping("/create/nickname/{nickname}")
    @ResponseStatus(HttpStatus.CREATED)
    fun createGame(@PathVariable nickname: String): GameResponse {
        //TODO: Move this to service
        val createdGame = gameService.createGame(
            GameRequest(playerList = emptyList(), owner = null).toDomain(
                "WAITING_FOR_PLAYERS",
                null
            )
        )

        // Create user
        val playerRequest = PlayerRequest(nickname = nickname, gameId = createdGame.id!!)
        val owner = gameService.joinGame(playerRequest.toDomain())

        // Update game with owner
        val ownerGame = createdGame.copy(owner = owner)
        val updatedGame = gameService.updateGame(ownerGame)
        return updatedGame.toResponse().copy(playerList = listOf(owner.toResponse()))
    }


    @PostMapping("/join/{id}/nickname/{nickname}")
    @ResponseStatus(HttpStatus.CREATED)
    fun joinGame(@PathVariable id: String, @PathVariable nickname: String): GameResponse {
        val playerRequest = PlayerRequest(nickname = nickname, gameId = id)
        gameService.joinGame(playerRequest.toDomain())
        simpMessagingTemplate.convertAndSend("/topic/$id", CommandMessageRequest(COMMAND.REFRESH, null).toResponse())
        return gameService.getGame(id).toResponse()
    }

    @PostMapping("/start/{id}/game-level/{gameLevel}")
    fun startGame(@PathVariable id: String, @PathVariable gameLevel: String): ResponseEntity<Unit> {
        try {
            gameService.setGameStart(id)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
//         Send Command to everyone in the lobby
        simpMessagingTemplate.convertAndSend("/topic/$id", CommandMessageRequest(COMMAND.STARTED, gameLevel).toResponse())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @MessageMapping("/topic/{id}/finished")
    fun finishGame(@DestinationVariable id: String, nickname: String) {
        gameService.setGameFinished(id, nickname)
        // Send Command to everyone in the lobby
        simpMessagingTemplate.convertAndSend("/topic/$id", CommandMessageRequest(COMMAND.FINISHED, null).toResponse())
    }
}