package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.EventService
import org.entur.norgesturbackend.norgestur.service.MultipleActiveEventsException
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
class EventController(private val eventService: EventService) {

    @GetMapping("/event/all")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @GetMapping("/event/{event}")
    fun getEventByEventName(
        @PathVariable event: String,
        ): Event? {
        return eventService.getEventByEventName(event)
    }

    @GetMapping("/event/active")
    fun getActiveEvent(): ResponseEntity<Any> {
        return try {
            val activeEvent = eventService.getActiveEvent()
            ResponseEntity.ok(activeEvent)
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to e.message))
        } catch (e: MultipleActiveEventsException) {
            ResponseEntity.status(409).body(mapOf("status" to 409, "error" to "Conflict", "message" to e.message))
        }
    }

    @PostMapping("/new-event")
    fun createEvent(@RequestBody event: Event): ResponseEntity<Event> {
        val savedEvent = eventService.addEvent(event)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent)
    }

    @PutMapping("/event/active/{eventId}")
    fun updateActiveEvent(
        @PathVariable eventId: Long,
    ): HttpStatus {
        return eventService.updateActiveEvent(eventId)
    }
}