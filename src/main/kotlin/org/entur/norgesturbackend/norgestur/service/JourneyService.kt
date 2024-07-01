package org.entur.norgesturbackend.norgestur.service

import org.entur.norgesturbackend.norgestur.model.Journey
import org.entur.norgesturbackend.norgestur.repository.JourneyRepository
import org.springframework.stereotype.Service

@Service
class JourneyService(private val journeyRepository: JourneyRepository) {

    fun getAllJourneys(): List<Journey> {
        return journeyRepository.findAllJourneys()
    }
}