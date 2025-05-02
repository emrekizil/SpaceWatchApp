plugins {
    alias(libs.plugins.spacewatchapp.android.library)
    alias(libs.plugins.spacewatchapp.android.hilt)
}

android {
    namespace = "com.emrekizil.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))


    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}