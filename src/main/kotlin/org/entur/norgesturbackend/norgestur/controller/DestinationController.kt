package org.entur.norgesturbackend.norgestur.controller

import org.entur.norgesturbackend.norgestur.model.Destination
import org.entur.norgesturbackend.norgestur.service.DestinationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DestinationController (val destinationService: DestinationService){
    @GetMapping("/destination")
    fun getDestination(): List<Destination>{
        return destinationService.getAll()
    }

    @GetMapping("/destination2")
    fun getDestinationByDestinationId(
            @RequestParam destinationId: String,
    ): Destination{
        return destinationService.getDestinationByDestinationId(destinationId)
    }
}