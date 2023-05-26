package org.entur.norgesturbackend.norgestur.model


enum class COMMAND { STARTED, FINISHED, REFRESH }

data class CommandMessageRequest(
    val command: COMMAND
)

data class CommandMessageResponse(
    val command: String
)

fun CommandMessageRequest.toResponse(): CommandMessageResponse {
    return CommandMessageResponse(
        command = command.name
    )
}

data class WinnerPlayer(
    val id: Int
)