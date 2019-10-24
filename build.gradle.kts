import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "in.arakaki"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	jcenter()
}

plugins {
	kotlin("jvm") version "1.3.21"
	kotlin("plugin.spring") version "1.3.50"
	id("org.springframework.boot") version "2.1.9.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

dependencies {
	compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")
	compile("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.10.0")
	compile("com.squareup.okhttp3:okhttp:3.2.0")
	compile("io.github.microutils:kotlin-logging:1.7.6")
	implementation("com.beust:klaxon:5.1")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}