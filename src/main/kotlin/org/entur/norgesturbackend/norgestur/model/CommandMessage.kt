package org.entur.norgesturbackend.norgestur.model


enum class COMMAND { STARTED, FINISHED, REFRESH }

data class CommandMessageRequest(
    val command: COMMAND,
    val gameLevel: String?
)

data class CommandMessageResponse(
    val command: String,
    val gameLevel: String?
)

fun CommandMessageRequest.toResponse(): CommandMessageResponse {
    return CommandMessageResponse(
        command = command.name,
        gameLevel = gameLevel
    )
}
