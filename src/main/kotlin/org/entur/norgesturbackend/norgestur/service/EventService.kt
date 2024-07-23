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

    fun getInactiveEvent(): List<Event> {
        return eventRepository.findEventsByIsActiveFalse()
    }

    @Transactional
    fun addOrUpdateEvent(event: Event): Event {
        eventRepository.deactivateAllEvents()
        val existingEvent = eventRepository.findEventByEventName(event.eventName)
        return if (existingEvent != null) {
            existingEvent.updateWith(event)
            eventRepository.save(existingEvent)
        } else {
            eventRepository.save(event)
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


    class MultipleActiveEventsException(message: String) : RuntimeException(message)
}