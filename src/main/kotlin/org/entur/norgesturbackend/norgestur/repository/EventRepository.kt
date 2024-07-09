package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional

interface EventRepository : JpaRepository<Event, Int> {

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


    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.isActive=false")
    fun deactivateAllEvents()
}