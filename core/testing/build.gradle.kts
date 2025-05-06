plugins {
    alias(libs.plugins.spacewatchapp.android.library)
}

android {
    namespace = "com.emrekizil.core.testing"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)

}