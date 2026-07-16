import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.4.0"
//	kotlin("plugin.spring") version "2.4.0"
//	id("org.springframework.boot") version "4.1.0"
//	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.vtech.euler"
version = "1.0.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	//vavr
	implementation("io.vavr:vavr:1.0.1")
	//arrow-kt
	implementation(platform("io.arrow-kt:arrow-stack:2.2.3"))
	// no versions on libraries
	implementation("io.arrow-kt:arrow-core")
	implementation("io.arrow-kt:arrow-eval")
	implementation("io.arrow-kt:arrow-fx-coroutines")
	implementation("io.arrow-kt:arrow-fx-stm")
	implementation("io.arrow-kt:arrow-resilience")
	implementation("io.arrow-kt:suspendapp")
	implementation("io.arrow-kt:arrow-functions-jvm")
	implementation("io.arrow-kt:arrow-core-serialization-jvm")
	implementation("io.arrow-kt:arrow-exception-utils")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict",
			"-Xannotation-default-target=param-property",
			"-Xcontext-parameters"
		)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}