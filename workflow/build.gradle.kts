plugins {
    id("java")
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(platform(libs.bom.spring.boot))
    implementation(libs.spring.boot.starter.starter)
    implementation(libs.slf4j)
    implementation(libs.logback.classic)
    annotationProcessor(libs.spring.boot.configuration.processor)
    implementation(libs.kafka.clients)
    implementation(project(":navigator-api"))
}

tasks.test {
    useJUnitPlatform()
}