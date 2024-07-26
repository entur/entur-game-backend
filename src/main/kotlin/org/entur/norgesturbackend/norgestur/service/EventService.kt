package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.entur.norgesturbackend.norgestur.repository.PlayerRepository
import org.entur.norgesturbackend.norgestur.repository.ScoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val scoreRepository: ScoreRepository,
    private val playerRepository: PlayerRepository
) {

    fun getAllEvents(): List<Event> {
        return eventRepository.findAllEvents()
    }

    fun getEventByEventName(eventName: String): Event? {
        return eventRepository.findEventByEventName(eventName)
    }

    fun getActiveEvent(): Event? {
        val activeEvents = eventRepository.findEventsByIsActiveTrue()
        if (activeEvents.size > 1) {
            throw MultipleActiveEventsException("Multiple active events found.")
        }
        return activeEvents.firstOrNull() ?: throw NoSuchElementException("No active event found.")
    }

    fun getInactiveEvents(): List<Event> {
        return eventRepository.findEventsByIsActiveFalse()
    }

    @Transactional
    @Throws(EventAlreadyExistsException::class)
    fun addOrUpdateEvent(event: Event): Event {
        eventRepository.deactivateAllEvents()
        val existingEvent = eventRepository.findEventByEventName(event.eventName)
        if (existingEvent != null) {
            throw EventAlreadyExistsException("Event with name ${event.eventName} already exists.")
        } else {
            return eventRepository.save(event)
        }
    }

    @Transactional
    fun updateActiveEvent(eventId: Long): HttpStatus {
        eventRepository.deactivateAllEvents()
        eventRepository.activateEvent(eventId)
        return HttpStatus.OK
    }

    @Transactional
    fun getEventByEventId(eventId: Long): Event? {
        return eventRepository.findEventByEventId(eventId)
    }

    @Transactional
    fun deleteEvent(eventId: Long) {
        val event = eventRepository.findById(eventId).orElseThrow {
            throw IllegalArgumentException("Event with id $eventId not found")
        }
        scoreRepository.deleteByEvent(event)
        eventRepository.delete(event)
        val playersWithNoScores = playerRepository.findPlayersWithNoScores()
        playerRepository.deleteAll(playersWithNoScores)
    }

    @Transactional
    fun endActiveEvent(): String {
        val activeEvent = eventRepository.findFirstByIsActive(true)
        return if (activeEvent != null) {
            activeEvent.isActive = false
            eventRepository.save(activeEvent)
            "Event ended successfully."
        } else {
            "No active event to end."
        }
    }

    @Throws(IllegalArgumentException::class)
    fun saveWinner(eventName: String, playerId: Long) {
        val event = eventRepository.findEventByEventName(eventName)
            ?: throw IllegalArgumentException("Event not found")

        val winner = playerRepository.findPlayerByPlayerId(playerId)
            ?: throw IllegalArgumentException("Playerr not found")
        event.winner = winner
        eventRepository.save(event)
    }

    class EventAlreadyExistsException(message: String) : RuntimeException(message)
    class MultipleActiveEventsException(message: String) : RuntimeException(message)
}