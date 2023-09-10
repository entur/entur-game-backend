package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.GameMode
import org.entur.norgesturbackend.norgestur.repository.GameModeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameModeService(val gameModeRepository: GameModeRepository, val playerScoreService: PlayerScoreService) {

    fun getGameModeById(difficulty: String): GameMode? {
        return gameModeRepository.findGameModeByDifficulty(difficulty)
    }

    fun getAllGameMode(): List<GameMode> {
        return gameModeRepository.findAll()
    }

    fun saveGameMode(gameMode: GameMode): HttpStatus {
        gameModeRepository.save(gameMode)
        return HttpStatus.CREATED
    }

    fun getOptimalRouteText(difficulty: String): String {
        val gameMode = gameModeRepository.findGameModeByDifficulty(difficulty)
        return "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + gameMode?.optimalRoute + " og reisetid er " + playerScoreService.calculateTravelTime(
            gameMode?.optimalTravelTime ?: 0
        )
    }

    fun getEvent(): GameMode? {
        return gameModeRepository.findGameModeByActiveEvent(true)
    }

    @Transactional
    fun updateEvent(difficulty: String): HttpStatus {
        val oldEvent = gameModeRepository.findGameModeByActiveEvent(true)?.copy(activeEvent = false)
        val newEvent = gameModeRepository.findGameModeByDifficulty(difficulty)?.copy(activeEvent = true)
            ?: return HttpStatus.NOT_FOUND

        if (oldEvent != null) gameModeRepository.save(oldEvent)
        gameModeRepository.save(newEvent)
        return HttpStatus.OK
    }

}