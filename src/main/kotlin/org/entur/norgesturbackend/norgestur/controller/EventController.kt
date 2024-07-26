package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.web.bind.annotation.*
import org.entur.norgesturbackend.norgestur.service.EventService
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
class EventController(private val eventService: EventService) {

    @GetMapping("/event/all")
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @GetMapping("/event/name/{eventName}")
    fun getEventByEventName(@PathVariable eventName: String): ResponseEntity<Any> {
        val event = eventService.getEventByEventName(eventName)
        return if (event != null) {
            ResponseEntity.ok(event)
        } else {
            ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to "Event not found"))
        }
    }


    @PostMapping("/end-event")
    fun endEvent(): ResponseEntity<String> {
        val message = eventService.endActiveEvent()
        return ResponseEntity.ok(message)
    }

    @GetMapping("/event/active")
    fun getActiveEvent(): ResponseEntity<Any> {
        return try {
            val activeEvent = eventService.getActiveEvent()
            ResponseEntity.ok(activeEvent)
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(404).body(mapOf("status" to 404, "error" to "Not Found", "message" to e.message))
        } catch (e: EventService.MultipleActiveEventsException) {
            ResponseEntity.status(409).body(mapOf("status" to 409, "error" to "Conflict", "message" to e.message))
        }
    }

    @GetMapping("/event/inactive")
    fun getInactiveEvents(): List<Event> {
        return eventService.getInactiveEvents()
    }


    @GetMapping("/event/id/{eventId}")
    fun getEventByEventId(@PathVariable eventId: Long): ResponseEntity<Any> {
        val eventById = eventService.getEventByEventId(eventId)
        return if (eventById != null) {
            ResponseEntity.ok(eventById)
        } else {
            ResponseEntity.status(204).body(mapOf("status" to 404, "message" to "Not Found"))
        }
    }

    @PostMapping("/new-event")
    fun createOrUpdateEvent(@RequestBody event: Event): ResponseEntity<Event> {
        val savedEvent = eventService.addOrUpdateEvent(event)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent)
    }

    @PutMapping("/event/active/{eventId}")
    fun updateActiveEvent(
        @PathVariable eventId: Long,
    ): HttpStatus {
        return eventService.updateActiveEvent(eventId)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteEvent(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            eventService.deleteEvent(id)
            ResponseEntity("Event deleted successfully", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(e.message, HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity("An error occurred: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}