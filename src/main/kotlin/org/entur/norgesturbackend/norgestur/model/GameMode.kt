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

data class NSRLocation(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
)
data class GameModeDto(
    val id: String,
    val name: String,
    val description: String,
    val start: NSRLocation,
    val targets: List<NSRLocation>,
    val difficulty: String,
    val optimalRoute: Int,
    val optimalTravelTime: Int,
)

fun GameMode.toDTO(): GameModeDto {
    return GameModeDto(
        id = id,
        name = gameName,
        description = gameDescription,
        start = NSRLocation(
            id = nsrStartLocation,
            name = nsrStartName,
            latitude = nsrStartLatitude,
            longitude = nsrStartLongitude,
        ),
        targets = listOf(
            NSRLocation(
                id = nsrEndLocation,
                name = nsrEndName,
                latitude = nsrStartLatitude, //TODO: Change to end latitude
                longitude = nsrStartLongitude, //TODO: Change to end longitude
            )
        ),
        difficulty = difficulty,
        optimalRoute = optimalRoute,
        optimalTravelTime = optimalTravelTime,
    )
}