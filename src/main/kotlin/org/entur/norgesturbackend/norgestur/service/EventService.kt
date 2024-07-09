package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(private val eventRepository: EventRepository) {

    fun getAllEvents(): List<Event> {
        return eventRepository.findAllEvents()
    }

    @Transactional
    fun saveEvent(event: Event): Event {
        eventRepository.deactivateAllEvents()
        return eventRepository.save(event)
    }


    fun getEventByEventName(eventName: String): Event? {
        return eventRepository.findEventByEventName(eventName)
    }

}