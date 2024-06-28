package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(private val testRepository: EventRepository) {

    fun getAllEvents(): List<Event> {
        return testRepository.findAllEvents()
    }
}