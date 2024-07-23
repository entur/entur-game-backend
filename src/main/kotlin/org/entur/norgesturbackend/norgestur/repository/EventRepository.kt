package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional

interface EventRepository : JpaRepository<Event, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event"
    )
    fun findAllEvents(): List<Event>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event WHERE event_name = (:eventName)"
    )
    fun findEventByEventName(
        @Param("eventName") eventName: String
    ): Event?

    @Query("SELECT e FROM Event e WHERE e.eventId = :eventId")
    fun findEventByEventId(@Param("eventId") eventId: Long): Event?

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event WHERE is_active = true"
    )
    fun findEventsByIsActiveTrue(): List<Event>

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM event WHERE is_active = false"
    )
    fun findEventsByIsActiveFalse(): List<Event>

    @Modifying
    @Transactional
    @Query("UPDATE Event event SET event.isActive=false WHERE event.isActive =true")
    fun deactivateAllEvents()

    @Modifying
    @Transactional
    @Query("UPDATE Event event SET event.isActive=true WHERE event.eventId=:eventId")
    fun activateEvent(@Param("eventId") eventId: Long)
}
