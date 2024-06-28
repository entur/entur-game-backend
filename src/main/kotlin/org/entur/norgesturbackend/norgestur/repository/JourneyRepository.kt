package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Journey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JourneyRepository : JpaRepository<Journey, Int> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM journey"
    )
    fun findAllJourneys(): List<Journey>
}