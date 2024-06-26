package org.entur.norgesturbackend.norgestur

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NorgesturApplication

fun main(args: Array<String>) {
	runApplication<NorgesturApplication>(*args)
}

