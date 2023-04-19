package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Destination
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DestinationController {
    @GetMapping("/destination")
    fun getDestination(): List<Destination>{
        return emptyList()
    }
}