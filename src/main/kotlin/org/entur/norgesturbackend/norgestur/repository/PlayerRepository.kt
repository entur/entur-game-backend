package org.entur.norgesturbackend.norgestur.repository

import org.entur.norgesturbackend.norgestur.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Int>{
    fun findByNickname(nickname: String): Player
}