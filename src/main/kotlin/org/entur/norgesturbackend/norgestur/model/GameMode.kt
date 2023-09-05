package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class GameMode(
    @Id
    val id: String,
    val gameName: String,
    val gameDescription: String,
    val nsrStartLocation: String,
    val nsrStartName: String,
    val nsrStartLatitude: Double,
    val nsrStartLongitude: Double,
    val nsrEndLocation: String,
    val nsrEndName: String,
    val difficulty: String,
    val optimalRoute: Int,
    val optimalTravelTime: Int,
)