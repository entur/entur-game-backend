package org.entur.norgesturbackend.norgestur.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins("*")
            .allowedHeaders("*")
    }

    @Bean
    fun createWebSocketContainer(): ServletServerContainerFactoryBean? {
        val container = ServletServerContainerFactoryBean()
        container.setMaxSessionIdleTimeout(600000L) // set the timeout to 10min
        return container
    }
}