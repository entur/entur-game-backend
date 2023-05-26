package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Destination
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DestinationRepository : JpaRepository<Destination, String> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM destination WHERE id = (:destinationId)"
    ) fun findDestinationByDestinationId(
            @Param("destinationId") destinationId: String
    ): Destination
}