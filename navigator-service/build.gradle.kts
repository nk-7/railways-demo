plugins {
    id("java")
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(platform(libs.bom.spring.boot))
    implementation(libs.spring.boot.starter.web)
    annotationProcessor(libs.spring.boot.configuration.processor)
    implementation(libs.spring.boot.starter.web)
    implementation(project(":navigator-api"))
    implementation("org.apache.commons:commons-lang3:3.17.0")
}

tasks.test {
    useJUnitPlatform()
}