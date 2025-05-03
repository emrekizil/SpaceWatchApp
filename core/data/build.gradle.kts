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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}