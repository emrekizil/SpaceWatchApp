plugins {
    alias(libs.plugins.spacewatchapp.android.library)
    alias(libs.plugins.spacewatchapp.android.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.spacewatchapp.android.room)
}

android {
    namespace = "com.emrekizil.core.database"
    
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.androidx.junit.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlin.test)
}