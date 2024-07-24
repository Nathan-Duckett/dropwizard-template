plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.dropwizard:dropwizard-bom:4.0.7"))

    implementation("io.dropwizard:dropwizard-core")
    implementation("io.dropwizard:dropwizard-json-logging")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}