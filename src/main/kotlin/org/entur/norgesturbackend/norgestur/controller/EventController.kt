package org.entur.norgesturbackend.norgestur.controller

import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.entur.norgesturbackend.norgestur.model.Event

@RestController
class EventController(private val eventService: EventService) {

    @GetMapping("/event/all")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @PostMapping("/new-event")
    fun createEvent(@RequestBody event: Event): ResponseEntity<Event> {
        val savedEvent = eventService.addEvent(event)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent)
    }

    @GetMapping("/event/{event}")
    fun getEventByEventName(
        @PathVariable event: String,
        ): Event? {
        return eventService.getEventByEventName(event)
    }
}