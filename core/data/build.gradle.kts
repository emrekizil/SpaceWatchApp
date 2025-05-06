plugins {
    alias(libs.plugins.spacewatchapp.android.library)
    alias(libs.plugins.spacewatchapp.android.hilt)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.emrekizil.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation(libs.turbine)
    testImplementation(libs.truth)
}