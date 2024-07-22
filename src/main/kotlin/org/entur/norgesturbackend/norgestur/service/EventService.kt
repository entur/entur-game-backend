package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(private val eventRepository: EventRepository) {

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

    @Transactional
    fun addEvent(event: Event): Event {
        eventRepository.deactivateAllEvents()
        val existingEvent = eventRepository.findEventByEventName(event.eventName)
        if (existingEvent != null) {
            val updatedEvent = existingEvent.copy(
                startLocationId = event.startLocationId,
                endLocationId = event.endLocationId,
                startTime = event.startTime,
                optimalStepNumber = event.optimalStepNumber,
                optimalTravelTime = event.optimalTravelTime,
                isActive = true
            )
            return eventRepository.save(updatedEvent)
        }
        return eventRepository.save(event)
    }

    @Transactional
    fun updateActiveEvent(eventId: Long): HttpStatus {
        eventRepository.deactivateAllEvents()
        eventRepository.activateEvent(eventId)
        return HttpStatus.OK
    }
}

class MultipleActiveEventsException(message: String) : RuntimeException(message)
