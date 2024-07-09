package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.springframework.stereotype.Service

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

}

class MultipleActiveEventsException(message: String) : RuntimeException(message)
