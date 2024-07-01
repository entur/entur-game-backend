package org.entur.norgesturbackend.norgestur.model

import jakarta.persistence.*

@Entity
@Table(name = "EVENT")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    val eventId: Long = 0,
    @Column(name = "eventName", nullable = false)
    val eventName: String,
    @ManyToOne
    @JoinColumn(name = "journeyId", nullable = false)
    val journey: Journey
)
