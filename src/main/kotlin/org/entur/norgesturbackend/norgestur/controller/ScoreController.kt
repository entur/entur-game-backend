package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Score
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.ScoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class ScoreController(private val scoreService: ScoreService) {

    @GetMapping("/score/all")
    fun getAllScores(): List<Score> {
        return scoreService.getAllScores()
    }

    @GetMapping("/score/active")
    fun getActiveScores(): List<Score> {
        return scoreService.getActiveScores()
    }

    //TODO: fiks stygg feilmelding dersom event ikke eksisterer eller player eksisterer med med annet email og/eller tlf.nummer
    //TODO: eksisterende event/player med nye score verdier -> suksess dvs. oppdateres
    //TODO: ny event/player kombo -> suksess dvs. ny event/player kombo legges til
    //TODO: dersom event ikke eksisterer-> stygg feilmelding (ønske: feilmelding med error kode)
    //TODO: dersom player ikke eksisterer-> suksess dvs. ønske: legg til player og så legg til score)
    //TODO: dersom playerNavn eksisterer men med annen email og/eller tlf. nummer -> oppfører seg som om det var punkt 2. (ønske: feilmelding)
    @PostMapping("/score/save")
    fun saveScore(
        @RequestBody score: Score,
    ): ResponseEntity<Any> {
        return scoreService.saveScore(score)
    }



}