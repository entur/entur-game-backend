package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.EventService

@RestController
class EventController(private val testService: EventService) {

    @GetMapping("/test/all")
    fun getAllEventsWithJourney(): List<Event> {
        return testService.getAllEvents()
    }
}