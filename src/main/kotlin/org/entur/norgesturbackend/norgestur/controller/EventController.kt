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
    fun createOrUpdateEvent(@RequestBody event: Event): ResponseEntity<Any> {
        return try {
            val savedEvent = eventService.addOrUpdateEvent(event)
            ResponseEntity(savedEvent, HttpStatus.OK)
        } catch (e: EventService.EventAlreadyExistsException) {
            ResponseEntity.status(409).body(mapOf("status" to 409, "error" to "Conflict", "message" to "Player with same name but different email and/or phone number already exists"))
        }
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

    @PostMapping("/save-winner")
    fun saveWinner(@RequestBody request: SaveWinnerRequest): ResponseEntity<Any> {
        return try {
            eventService.saveWinner(request.eventName, request.playerId)
            ResponseEntity.status(HttpStatus.OK).body(mapOf("status" to "success"))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("status" to 400, "error" to "Bad Request", "message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("status" to 500, "error" to "Internal Server Error", "message" to e.message))
        }
    }
}

data class SaveWinnerRequest(
    val eventName: String,
    val playerId: Long
)