package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.GameMode
import org.entur.norgesturbackend.norgestur.repository.GameModeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class GameModeService (val gameModeRepository: GameModeRepository, val playerScoreService: PlayerScoreService){



    fun getGameModeById(difficulty: String): GameMode{
        return gameModeRepository.findGameModeByDifficulty(difficulty)
    }

    fun saveGameMode(gameMode: GameMode): HttpStatus{
        gameModeRepository.save(gameMode)
        return HttpStatus.CREATED
    }

    fun getOptimalRouteText(difficulty: String): String{
        val gameMode = gameModeRepository.findGameModeByDifficulty(difficulty)
        return "Vår reiseplanlegger har beregnet en optimal rute der etapper er " + gameMode.optimalRoute + " og reisetid er " + playerScoreService.calculateTravelTime(gameMode.optimalTravelTime)
    }
}