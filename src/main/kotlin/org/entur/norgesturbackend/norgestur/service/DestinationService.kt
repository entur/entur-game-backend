package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Destination
import org.entur.norgesturbackend.norgestur.repository.DestinationRepository
import org.springframework.stereotype.Service

@Service
class DestinationService(val destinationRepository: DestinationRepository) {
    fun getAll(): List<Destination>{
        return destinationRepository.findAll()
    }
}