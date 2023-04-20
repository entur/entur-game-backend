package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.TextMessageDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class WebSocketTextController {
    @Autowired
    var template: SimpMessagingTemplate? = null
    @PostMapping("/send")
    fun sendMessage(@RequestBody textMessageDTO: TextMessageDTO?): ResponseEntity<Void> {
        template!!.convertAndSend("/topic/message", textMessageDTO!!)
        return ResponseEntity(HttpStatus.OK)
    }

    @MessageMapping("/sendMessage")
    fun receiveMessage(@Payload textMessageDTO: TextMessageDTO?) {
        // receive message from client
    }

    @SendTo("/topic/message")
    fun broadcastMessage(@Payload textMessageDTO: TextMessageDTO): TextMessageDTO {
        return textMessageDTO
    }
}