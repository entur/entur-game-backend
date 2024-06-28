package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.model.EventJourneyDto
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.EventService

@RestController
class EventController(private val testService: EventService) {

    @GetMapping("/test/testDb")
    fun getTestDb(): List<Event> {
        return testService.getTestDb()
    }

    @GetMapping("/test/eventJourneys")
    fun getAllEventsWithJourney(): List<EventJourneyDto> {
        return testService.getAllEventsWithJourney()
    }
}