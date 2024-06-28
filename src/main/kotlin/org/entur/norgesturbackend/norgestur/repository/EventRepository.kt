package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Event
import org.entur.norgesturbackend.norgestur.model.EventJourneyDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EventRepository : JpaRepository<Event, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event"
    )
    fun findAllEvents(): List<Event>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event JOIN journey ON event.journey_id = journey.id"
    )
    fun findAllEventsWithJourney(): List<EventJourneyDto>
}