plugins {
    alias(libs.plugins.spacewatchapp.android.library)
    alias(libs.plugins.spacewatchapp.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.emrekizil.core.database"
    
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.androidx.junit.ktx)
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlin.test)
}