import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.jpa") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
}

group = "org.entur.norgestur-backend"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework:spring-websocket:6.0.12")
	implementation("org.springframework:spring-messaging:6.0.12")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.bootJar {
	archiveFileName.set("entur-game-backend.jar")
}

tasks.getByName<Jar>("jar") {
	enabled = false
}