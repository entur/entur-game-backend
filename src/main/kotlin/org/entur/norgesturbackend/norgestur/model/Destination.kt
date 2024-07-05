package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Destination(
    @Id
    val id: String,
    val destination: String
)