dependencies {
    implementation(platform(libs.bom.jackson))
    implementation(libs.jackson.databind)
    implementation(project(":navigator-api"))
}
