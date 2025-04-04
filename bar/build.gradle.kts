plugins {
    id("java")
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(platform(libs.bom.spring.boot))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.slf4j)
    implementation(libs.logback.classic)
    annotationProcessor(libs.spring.boot.configuration.processor)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.kafka.clients)
    implementation(project(":navigator-api"))
    implementation(project(":navigator-client"))
}

tasks.test {
    useJUnitPlatform()
}