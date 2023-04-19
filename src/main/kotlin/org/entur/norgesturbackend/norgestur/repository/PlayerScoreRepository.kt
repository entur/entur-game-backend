package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.PlayerScore
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerScoreRepository : JpaRepository<PlayerScore, Int>{
}