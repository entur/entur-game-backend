package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Message
import org.entur.norgesturbackend.norgestur.model.OutputMessage.OutputMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

@RestController
class MessageController{

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun send(message: Message): OutputMessage? {
        val time = SimpleDateFormat("HH:mm").format(Date())
        return OutputMessage(message.from , message.text, time)
    }

}
