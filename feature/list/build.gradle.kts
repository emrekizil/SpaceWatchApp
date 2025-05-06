plugins {
    alias(libs.plugins.spacewatchapp.android.library.compose)
    alias(libs.plugins.spacewatchapp.android.feature)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.emrekizil.feature.list"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:testing"))
    implementation(libs.androidx.ui.test.junit4.android)

    testImplementation(libs.junit)

    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.ui.test.manifest)
}