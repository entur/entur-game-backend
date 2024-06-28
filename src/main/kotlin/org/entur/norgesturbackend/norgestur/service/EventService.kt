package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.model.EventJourneyDto
import org.entur.norgesturbackend.norgestur.model.Journey
import org.entur.norgesturbackend.norgestur.repository.EventRepository
import org.entur.norgesturbackend.norgestur.repository.JourneyRepository
import org.springframework.stereotype.Service

@Service
class EventService(private val testRepository: EventRepository) {

    fun getTestDb(): List<Event> {
        return testRepository.findAllEvents()
    }

    fun getAllEventsWithJourney(): List<EventJourneyDto> {
        return testRepository.findAllEventsWithJourney()
    }
}