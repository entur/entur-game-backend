package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EventRepository : JpaRepository<Event, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT event_id, event.name, journey.journey_id, journey.startid, journey.stopid, journey.datetime, journey.description, journey.optimaljourney, journey.optimaltraveltime FROM event JOIN journey ON event.journey_id = journey.journey_id"
    )
    fun findAllEvents(): List<Event>
}