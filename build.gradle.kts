plugins {
    id("java")
    id("com.google.cloud.tools.jib") version("3.4.4")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.dropwizard:dropwizard-bom:4.0.10"))

    implementation("io.dropwizard:dropwizard-core")
    implementation("io.dropwizard:dropwizard-json-logging")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

jib {
    extraDirectories.setPaths("config/")
    to {
        image = "nathanduckett/${name}:latest"
        tags = setOf("latest", version.toString())
    }
    container {
        args = listOf("server", "service.yml")
    }
}