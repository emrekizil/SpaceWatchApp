plugins {
    alias(libs.plugins.spacewatchapp.android.library.compose)
    alias(libs.plugins.spacewatchapp.android.feature)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.emrekizil.feature.list"
}

dependencies {
    implementation(project(":core:testing"))

    testImplementation(libs.junit)
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}