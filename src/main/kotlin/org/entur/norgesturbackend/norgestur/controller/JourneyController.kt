package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Journey
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.JourneyService

@RestController
class JourneyController(private val journeyService: JourneyService) {

    @GetMapping("/test/all")
    fun getAllJourneysWithJourney(): List<Journey> {
        return journeyService.getAllJourneys()
    }
}