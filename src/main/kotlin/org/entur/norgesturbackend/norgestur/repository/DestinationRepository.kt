package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Destination
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationRepository : JpaRepository<Destination, String> {
}