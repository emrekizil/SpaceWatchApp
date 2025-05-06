import com.emrekizil.build_logic.convention.implementation

plugins {
    alias(libs.plugins.spacewatchapp.android.application)
    alias(libs.plugins.spacewatchapp.android.application.compose)
    alias(libs.plugins.spacewatchapp.android.hilt)
    alias(libs.plugins.compose.compiler)
    id("androidx.room") version "2.6.1" apply false
}

android {
    namespace = "com.emrekizil.spacewatchapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.emrekizil.spacewatchapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(project(":feature:list"))
    implementation(project(":feature:detail"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:ui"))
    implementation(project(":core:testing"))
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}